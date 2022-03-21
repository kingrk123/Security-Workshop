/**
 * 
 */
package com.medilab.preclinic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bprayaga
 *
 */
@Entity
@Table(name = "UserPermission")
@Data

public class UserPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public UserPermission() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPermission(String name, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
		super();
		this.name = name;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}
	@Column(nullable = false,unique = true, updatable = true)
	private String name;
	
	/**
	 * Audit info
	 */
	private String createdBy;
	private Date createdDate;
	
	private String modifiedBy;
	private Date modifiedDate;
	

}
