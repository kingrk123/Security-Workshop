package com.medilab.preclinic.config;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.model.UserRole;
import com.medilab.preclinic.model.UserRoleToPermission;
import com.medilab.preclinic.repo.MedilabUserRepository;

@Component
public class MedilabUserPermissions implements PermissionEvaluator {

	@Autowired
	private MedilabUserRepository userRepo;
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		System.out.println("custom permissions evaluator");
		String moduleName = (String) targetDomainObject;
		System.out.println("module name:\t"+moduleName);
		String permissionName = (String) permission;
		System.out.println("permission Name:\t"+permissionName);
		String loggedInUser = authentication.getName();
		System.out.println("logged in user:\t"+loggedInUser);
		
		MedilabUser user = userRepo.findUserByEmail(loggedInUser);
		UserRole userRole = user.getRole();
		if(userRole != null && userRole.getName().equals(moduleName)) {
		  Set<UserRoleToPermission>	permSet =  userRole.getPermissionsSet();
		  if(permSet != null && permSet.size() >0) {
			  for(UserRoleToPermission urtp : permSet) {
				  if(permissionName.equalsIgnoreCase(urtp.getUserPermission().getName())) {
					  return true;
				  }
			  }
		  }
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return true;
	}

}
