/**
 * 
 */
package com.medilab.preclinic.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medilab.preclinic.MedilabUserManagementApplicationTests;
import com.medilab.preclinic.bean.UserTypeBean;

/**
 * @author IM-LP-1763
 *
 */
public class UserTypeServiceTest extends MedilabUserManagementApplicationTests {

	@Autowired
	private UserTypeService userTypeService;
	
	@Test
	public void testCreateUserType() {
		
		UserTypeBean userTypeBean = new UserTypeBean();
		userTypeBean.setUserType("doctor");
		
		userTypeBean = userTypeService.createUserType(userTypeBean);
		
		assertNotNull(userTypeBean);
		assertNotNull(userTypeBean.getId());
		
	}
	
}
