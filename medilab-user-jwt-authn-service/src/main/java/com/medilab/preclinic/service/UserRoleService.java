/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.List;

import com.medilab.preclinic.bean.UserRoleBean;
import com.medilab.preclinic.model.UserRole;

/**
 * @author IM-LP-1763
 *
 */
public interface UserRoleService {

	public UserRoleBean createRole(final UserRoleBean roleBean);
	public UserRoleBean findRoleById(final String roleId);
	public UserRoleBean updateRole(final UserRoleBean roleBean);
	public List<UserRoleBean> deleteRole(final UserRoleBean roleBean);
	public List<UserRoleBean> deleteRole(final String roleId);
	
	/**
	 * provisioning and deprovisioning operations
	 */
	public UserRoleBean provisioningUser(String userId, String roleId);
	public UserRoleBean deProvisioningUser(String userId, String roleId);
	
	public List<UserRoleBean> findAllRoles();
	UserRole mapBeanToDomain(UserRoleBean UserRoleBean);
	UserRoleBean mapDomainToBean(UserRole roleDomain);
	
}
