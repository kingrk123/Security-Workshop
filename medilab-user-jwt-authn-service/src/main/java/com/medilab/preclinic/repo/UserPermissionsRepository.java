package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medilab.preclinic.model.UserPermission;


public interface UserPermissionsRepository extends JpaRepository<UserPermission, Integer> {

	@Query(name = "findPermissionByName",value = "from UserPermission user where user.name=:name")
	public UserPermission findPermissionByName(@Param("name") String name);
}
