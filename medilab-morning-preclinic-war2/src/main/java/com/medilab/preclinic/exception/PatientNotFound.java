package com.medilab.preclinic.exception;

public class PatientNotFound extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public PatientNotFound() {
		// TODO Auto-generated constructor stub
	}
	public PatientNotFound(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	
}
