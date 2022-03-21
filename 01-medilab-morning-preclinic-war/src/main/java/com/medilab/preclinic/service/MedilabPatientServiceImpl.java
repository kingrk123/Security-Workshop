/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medilab.preclinic.bean.PatientBean;
import com.medilab.preclinic.exception.PatientNotFound;
import com.medilab.preclinic.model.Patient;
import com.medilab.preclinic.repo.PatientRepo;

/**
 * @author nsanda
 *
 */
@Service
public class MedilabPatientServiceImpl implements MedilabPatientService {

	@Autowired
	private PatientRepo patRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#save(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	public PatientBean save(PatientBean patBean) {
		Patient patModel = new Patient();
		BeanUtils.copyProperties(patBean, patModel);
		patRepo.save(patModel);

		BeanUtils.copyProperties(patModel, patBean);	

		return patBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#findAll()
	 */
	@Override
	public List<PatientBean> findAll() {
		List<Patient> patModelList = patRepo.findAll();
		List<PatientBean> patBeanList = new ArrayList<>();
		if (patModelList != null && patModelList.size() > 0) {
			patModelList.stream().forEach(patModel -> {
				PatientBean patBean = new PatientBean();
				BeanUtils.copyProperties(patModel, patBean);
				patBeanList.add(patBean);
			});
		}
		return patBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#findById(int)
	 */
	@Override
	public PatientBean findById(int id) throws PatientNotFound {
		Optional<Patient> patOpt = patRepo.findById(id);
		PatientBean patBean = new PatientBean();
		if(patOpt.isPresent()) {
			Patient patModel = patOpt.get();
			BeanUtils.copyProperties(patModel, patBean);
					
			return patBean;
		}else {
			throw new PatientNotFound("No Patient found with the patient ID:\t"+id);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medilab.preclinic.service.MedilabDoctorService#findByName(java.lang.
	 * String)
	 */
	@Override
	public PatientBean findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#delete(int)
	 */
	@Override
	public List<PatientBean> delete(int id) {
		patRepo.deleteById(id);
		return findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medilab.preclinic.service.MedilabDoctorService#delete(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	public List<PatientBean> delete(PatientBean deptBean) {
		Patient Doctor = new Patient();
		BeanUtils.copyProperties(deptBean, Doctor);
		patRepo.delete(Doctor);
		return findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medilab.preclinic.service.MedilabDoctorService#update(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,
	propagation=Propagation.REQUIRED)
	public PatientBean update(PatientBean patBean) {
		
		Patient patModel = new Patient();
		BeanUtils.copyProperties(patBean, patModel);
		patRepo.save(patModel);		
		BeanUtils.copyProperties(patModel, patBean);
		
		
		
		return patBean;
	}

}
