package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.MedilabUser;

public interface MedilabUserRepository extends JpaRepository<MedilabUser, Integer> {

}
