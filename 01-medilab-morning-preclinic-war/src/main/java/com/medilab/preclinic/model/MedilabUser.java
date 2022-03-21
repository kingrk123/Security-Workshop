package com.medilab.preclinic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "MedilabUser")
@Data
public class MedilabUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	private String password;
	
	@OneToOne
	private UserRole role;
	
	//@OneToOne
	//private Address postalAddress;
	
	@OneToOne
	private MedilabUserType userType;
	
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Set<Document> docsList;
	
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Set<Notification> notificationsList;
	
	//@OneToOne
	//private Department dept;
	
	
}
