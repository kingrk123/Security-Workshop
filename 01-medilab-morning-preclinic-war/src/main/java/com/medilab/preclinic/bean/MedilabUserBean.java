package com.medilab.preclinic.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedilabUserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	
	private String dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	
	private UserRoleBean role;
	
	private UserTypeBean userType;
}
