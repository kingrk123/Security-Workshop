/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.List;

import com.medilab.preclinic.bean.MedilabUserBean;

/**
 * @author IM-LP-1763
 *
 */
public interface UserProfileService {

	public MedilabUserBean createUser(final MedilabUserBean UserBean, String callbackUrl);
	public MedilabUserBean findUserById(final String UserId);
	public MedilabUserBean updateUser(final MedilabUserBean UserBean);
	public List<MedilabUserBean> deleteUser(final MedilabUserBean UserBean);
	public List<MedilabUserBean> deleteUser(final String UserId);
	
	/**
	 * provisioning and deprovisioning operations
	 */
	public MedilabUserBean provisioningUser(String userId, String roleId);
	public MedilabUserBean deProvisioningUser(String userId, String roleId);
	
	public List<MedilabUserBean> findAllUsers();
	
	public boolean verifyUser(String verificationCode);
	
}
