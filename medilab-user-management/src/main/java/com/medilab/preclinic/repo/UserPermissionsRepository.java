package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.UserPermission;

public interface UserPermissionsRepository extends JpaRepository<UserPermission, Integer> {

}
