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

import com.medilab.preclinic.bean.DoctorBean;
import com.medilab.preclinic.exception.DoctorCreationException;
import com.medilab.preclinic.exception.DoctorNotFound;
import com.medilab.preclinic.service.MedilabDoctorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class DoctorRestController {

	@Autowired
	private MedilabDoctorService deptService;
	
	//@RequestMapping(value="/Doctors",method=RequestMethod.GET)
	@GetMapping("/Doctors")
	public List<DoctorBean> viewDoctorsBoard() {
		log.info("i am in Doctors");
		
		List<DoctorBean> deptBeanList = deptService.findAll();
		
		return deptBeanList;
	}
	
	//@RequestMapping(value="/Doctors/{deptId}",method=RequestMethod.GET)
	@GetMapping("/Doctors/{deptId}")
	public ResponseEntity<Object> getDoctor(@PathVariable("deptId") int deptId) throws DoctorNotFound {
		log.info("deprtment id is:\t"+deptId);
		//try {
			DoctorBean deptBeanList = deptService.findById(deptId);
			return ResponseEntity.ok(deptBeanList);
		/*} catch (DoctorNotFound e) {
			ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), 
					e.getMessage(), 
					new Date(), 
					e.getMessage()) ;
			return ResponseEntity.ok(error);
		}*/
	}
	
	//addDept
	//@RequestMapping(value="/Doctors",method=RequestMethod.POST)
	@PostMapping(value="/Doctors")
	public List<DoctorBean> addDept(@Valid @RequestBody DoctorBean deptbean) throws DoctorCreationException {
		log.info("deptbean data is:\t"+deptbean.toString());
		deptbean = deptService.save(deptbean);
		List<DoctorBean> deptBeanList = deptService.findAll();
	
		return deptBeanList;
	}
	
	//@RequestMapping(value="/Doctors/{deptId}",method=RequestMethod.DELETE)
	@DeleteMapping("/Doctors/{deptId}")
	public List<DoctorBean> deleteDoctor(@PathVariable("deptId") int deptId) {
		log.info("deprtment id is:\t"+deptId);
		
		List<DoctorBean> deptBeanList = deptService.delete(deptId);
		return deptBeanList;
	}
	
	/*@RequestMapping("/editDept/{deptId}")
	public String editDoctorPage(@PathVariable("deptId") int deptId,Model model) {
		log.info("deprtment id is:\t"+deptId);
		DoctorBean deptBean = deptService.findById(deptId);
		model.addAttribute("deptBean", deptBean);
		return "Doctor/editDoctors";
	}*/
	
	//@RequestMapping(value="/Doctors",method=RequestMethod.PUT)
	@PutMapping("/Doctors")
	public List<DoctorBean> updateDept(@RequestBody DoctorBean deptbean) throws DoctorCreationException {
		log.info("updateDept data is:\t"+deptbean.toString());
		deptbean = deptService.save(deptbean);
		
		List<DoctorBean> deptBeanList = deptService.findAll();
		return deptBeanList;
	}
}
