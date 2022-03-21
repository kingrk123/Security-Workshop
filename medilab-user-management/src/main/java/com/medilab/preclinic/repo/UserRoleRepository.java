package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
