/**
 * 
 */
package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.model.Patient;

/**
 * @author nsanda
 *
 */
public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
