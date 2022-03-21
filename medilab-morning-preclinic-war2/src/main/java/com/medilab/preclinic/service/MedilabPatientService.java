/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.List;

import com.medilab.preclinic.bean.PatientBean;
import com.medilab.preclinic.exception.PatientNotFound;

/**
 * @author nsanda
 *
 */
public interface MedilabPatientService {

	public PatientBean save(PatientBean patBean);
	public List<PatientBean> findAll();
	public PatientBean findById(int id) throws PatientNotFound;
	public PatientBean findByName(String name);
	public List<PatientBean> delete(int id);
	public List<PatientBean> delete(PatientBean patBean);
	public PatientBean update(PatientBean patBean);
	
}
