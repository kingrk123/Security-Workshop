package com.medilab.preclinic.exception;

import java.time.LocalDate;

public class PatientCreationException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private LocalDate time;
	private String createdBy;
	public LocalDate getTime() {
		return time;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public PatientCreationException() {
		// TODO Auto-generated constructor stub
	}
	public PatientCreationException(String message) {
		this.message=message;
		this.time=LocalDate.now();
		this.createdBy="admin";
	}
	public String getMessage() {
		return message;
	}
	
}
