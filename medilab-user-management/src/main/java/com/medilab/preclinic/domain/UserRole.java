package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "UserRole")
@Data
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(nullable = false,unique = true, updatable = true)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER, 
			mappedBy = "userRole")
	private Set<UserRoleToPermission> permissionsSet=new HashSet<UserRoleToPermission>();

}
