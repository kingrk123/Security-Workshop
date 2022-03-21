package com.medilab.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

public class UserRolesTest extends MedilabUserManagementApplicationTests {

	@Autowired
	private UserPermissionsRepository permissionsRepo;
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	@Test
	public void createRole() {
		List<UserPermission> permissionsList = permissionsRepo.findAll();
		System.out.println(permissionsList.size());
		assertNotNull(permissionsList);
		
		UserRole role = new UserRole();
		role.setName("doctor");
		
		Set<UserRoleToPermission> permissionsSet = new HashSet<UserRoleToPermission>();
		if(permissionsList != null && permissionsList.size() >0) {
			permissionsList.stream().forEach(up->{
				UserRoleToPermission roleToPermission = new UserRoleToPermission();
				roleToPermission.setUserPermission(up);
				//roleToPermission.setUserRole(role);
				permissionsSet.add(roleToPermission);
			});
		}
		
		role.setPermissionsSet(permissionsSet);
		roleRepository.save(role);
		assertNotNull(role.getId());
		
	}
}
