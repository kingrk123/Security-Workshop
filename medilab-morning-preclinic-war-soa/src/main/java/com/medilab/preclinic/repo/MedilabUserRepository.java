package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.model.MedilabUser;


public interface MedilabUserRepository extends JpaRepository<MedilabUser, Integer> {

}
