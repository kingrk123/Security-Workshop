package com.medilab.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medilab.preclinic.domain.UserPermission;
import com.medilab.preclinic.domain.UserRole;
import com.medilab.preclinic.domain.UserRoleToPermission;
import com.medilab.preclinic.repo.UserPermissionsRepository;
import com.medilab.preclinic.repo.UserRoleRepository;

public class UserPermissionsTest extends MedilabUserManagementApplicationTests {

	@Autowired
	private UserPermissionsRepository permissionsRepo;
	
	@Test
	public void createPermission() {
		String name="add";
		String createdBy="admin";
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		UserPermission permission = new UserPermission(name, createdBy, date, createdBy, date);
		permissionsRepo.save(permission);
		
		assertNotNull(permission.getId());	
		
		List<UserPermission> permissionsList = permissionsRepo.findAll();
		System.out.println(permissionsList.size());
		assertNotNull(permissionsList);
		
	}
		
	//}
	
	/*
	 * @Test public void createRole() { List<UserPermission> permissionsList =
	 * permissionsRepo.findAll(); System.out.println(permissionsList.size());
	 * assertNotNull(permissionsList);
	 * 
	 * UserRole role = new UserRole(); role.setName("medilabAdmin");
	 * 
	 * Set<UserRoleToPermission> permissionsSet = new
	 * HashSet<UserRoleToPermission>(); if(permissionsList != null &&
	 * permissionsList.size() >0) { permissionsList.stream().forEach(up->{
	 * UserRoleToPermission roleToPermission = new UserRoleToPermission();
	 * roleToPermission.setUserPermission(up); roleToPermission.setUserRole(role);
	 * permissionsSet.add(roleToPermission); }); }
	 * 
	 * role.setPermissionsSet(permissionsSet); roleRepository.save(role);
	 * assertNotNull(role.getId());
	 * 
	 * }
	 */
}
