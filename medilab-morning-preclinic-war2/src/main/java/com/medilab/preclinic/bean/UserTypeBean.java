package com.medilab.preclinic.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserTypeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Id;
	
	private String userType;

}
