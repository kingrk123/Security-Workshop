package com.medilab.preclinic.util;

import lombok.Data;
import lombok.Getter;

public enum RoleEnum {

	
	ADMIN(1,"Admin"),
	DOCTOR(2, "Doctor");
	
	private Integer roleId;
	private String role;
		
	public Integer getRoleId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}

	RoleEnum(int roleId, String role){
		this.role = role;
		this.roleId = roleId;
	}
	
	public static String getRole(String role) {
		return RoleEnum.valueOf(role).name();
	}
	
	public static RoleEnum getRoleByName(String role) {
		for(RoleEnum roleEnum : RoleEnum.values()) {
			if(roleEnum.role.equals(role)) {
				return roleEnum;
			}
		}
		return null;
	}
}
