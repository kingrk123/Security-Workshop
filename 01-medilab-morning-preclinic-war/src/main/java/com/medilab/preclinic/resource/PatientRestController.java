package com.medilab.preclinic.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilab.preclinic.bean.PatientBean;
import com.medilab.preclinic.exception.PatientCreationException;
import com.medilab.preclinic.exception.PatientNotFound;
import com.medilab.preclinic.service.MedilabPatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PatientRestController {

	@Autowired
	private MedilabPatientService patService;
	
	//@RequestMapping(value="/patients",method=RequestMethod.GET)
	@GetMapping("/patients")
	public List<PatientBean> viewpatientsBoard() {
		log.info("i am in patients");
		
		List<PatientBean> patBeanList = patService.findAll();
		
		return patBeanList;
	}
	
	//@RequestMapping(value="/patients/{patId}",method=RequestMethod.GET)
	@GetMapping("/patients/{patId}")
	public ResponseEntity<Object> getDoctor(@PathVariable("patId") int patId) throws PatientNotFound {
		log.info("deprtment id is:\t"+patId);
		//try {
			PatientBean patBeanList = patService.findById(patId);
			return ResponseEntity.ok(patBeanList);
		/*} catch (DoctorNotFound e) {
			ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), 
					e.getMessage(), 
					new Date(), 
					e.getMessage()) ;
			return ResponseEntity.ok(error);
		}*/
	}
	
	//addDept
	//@RequestMapping(value="/patients",method=RequestMethod.POST)
	@PostMapping(value="/patients")
	public List<PatientBean> addDept(@Valid @RequestBody PatientBean patBean) throws PatientCreationException {
		log.info("patBean data is:\t"+patBean.toString());
		patBean = patService.save(patBean);
		List<PatientBean> patBeanList = patService.findAll();
	
		return patBeanList;
	}
	
	//@RequestMapping(value="/patients/{patId}",method=RequestMethod.DELETE)
	@DeleteMapping("/patients/{patId}")
	public List<PatientBean> deleteDoctor(@PathVariable("patId") int patId) {
		log.info("deprtment id is:\t"+patId);
		
		List<PatientBean> patBeanList = patService.delete(patId);
		return patBeanList;
	}
	
	/*@RequestMapping("/editDept/{patId}")
	public String editDoctorPage(@PathVariable("patId") int patId,Model model) {
		log.info("deprtment id is:\t"+patId);
		DoctorBean patBean = patService.findById(patId);
		model.addAttribute("patBean", patBean);
		return "Doctor/editpatients";
	}*/
	
	//@RequestMapping(value="/patients",method=RequestMethod.PUT)
	@PutMapping("/patients")
	public List<PatientBean> updateDept(@RequestBody PatientBean patBean) throws PatientCreationException {
		log.info("updateDept data is:\t"+patBean.toString());
		patBean = patService.save(patBean);
		
		List<PatientBean> deptBeanList = patService.findAll();
		return deptBeanList;
	}
}
