/**
 * 
 */
package com.medilab.preclinic.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bprayaga
 *
 */

@Data
@NoArgsConstructor
public class UserPermissionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	public UserPermissionBean(String name) {
		super();
		this.name = name;
	}
	private String name;
	

}
