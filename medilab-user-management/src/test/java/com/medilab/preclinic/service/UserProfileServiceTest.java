/**
 * 
 */
package com.medilab.preclinic.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medilab.preclinic.MedilabUserManagementApplicationTests;
import com.medilab.preclinic.bean.MedilabUserBean;

/**
 * @author IM-LP-1763
 *
 */
public class UserProfileServiceTest extends MedilabUserManagementApplicationTests {

	@Autowired
	private UserProfileService userService;
	
	MedilabUserBean userBean = null;
	static String USER_FIRST_NAME = "Alex";
	static String USER_LAST_NAME = "Ben";
	static String uSER_EMAIL = "benAlex@spring.org";
	static String uSER_ID = "AlexB";
	static String uSER_GENDER = "Male";
	static String uSER_BIOGRAPHY = "Founder Of Acegi Security";
	static boolean uSER_STATUS = false;
	
	static String uSER_DOB = "09/10/1974";
	
	static String uSER_ROLE = "";
	static String uSER_TYPE = "";
	
	/**
	 * for provisioning and deprovisioning
	 */
	static String user_ROLE_ID = "1";
	static String user_ID_PRIMARY="2";
	
	@BeforeEach
	public void init() {
		userBean = new MedilabUserBean();
		userBean.setFirstName(USER_FIRST_NAME);
		userBean.setLastName(USER_LAST_NAME);
		userBean.setEmail(uSER_EMAIL);
		userBean.setUserId(uSER_ID);
		userBean.setGender(uSER_GENDER);
		userBean.setShortBiography(uSER_BIOGRAPHY);
		userBean.setStatus(uSER_STATUS);
		userBean.setDob(uSER_DOB);
	}
	
	@Disabled
	@Test
	public void testCreateUserProfile() {
		userBean = userService.createUser(userBean,null);
		assertNotNull(userBean);
		assertNotNull(userBean.getId());
	}
	
	@Disabled
	@Test
	public void testDeprovisioning() {
		MedilabUserBean userBean = userService.deProvisioningUser(user_ID_PRIMARY, user_ROLE_ID);
		assertNotNull(userBean);
		assertNull(userBean.getRole());
	}
	
	@Test
	public void testProvisioning() {
		MedilabUserBean userBean = userService.provisioningUser(user_ID_PRIMARY, user_ROLE_ID);
		assertNotNull(userBean);
		assertNotNull(userBean.getRole());
	}
	
	
}
