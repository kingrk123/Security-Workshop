package com.medilab.preclinic.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Id;
	
	private String name;
	
	
	private Set<UserPermissionBean> permissionsSet=new HashSet<UserPermissionBean>();

}
