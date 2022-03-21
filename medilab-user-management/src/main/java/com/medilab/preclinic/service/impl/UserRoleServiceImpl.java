package com.medilab.preclinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilab.preclinic.bean.UserRoleBean;
import com.medilab.preclinic.domain.UserRole;
import com.medilab.preclinic.repo.UserRoleRepository;
import com.medilab.preclinic.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleRepository roleRepo;

	@Override
	public UserRoleBean createRole(UserRoleBean roleBean) {
		return mapDomainToBean(roleRepo.save(mapBeanToDomain(roleBean)));
	}

	@Override
	public UserRoleBean findRoleById(String roleId) {
		Optional<UserRole> roleDomainOptional = roleRepo.findById(Integer.valueOf(roleId));
		return mapDomainToBean(roleDomainOptional.get());
	}

	@Override
	public UserRoleBean updateRole(UserRoleBean roleBean) {
		return mapDomainToBean(roleRepo.save(mapBeanToDomain(roleBean)));
	}

	@Override
	public List<UserRoleBean> deleteRole(UserRoleBean roleBean) {
		roleRepo.delete(mapBeanToDomain(roleBean));
		return findAllRoles();
	}

	@Override
	public List<UserRoleBean>  deleteRole(String roleId) {
		roleRepo.deleteById(Integer.valueOf(roleId));
		return findAllRoles();
	}

	@Override
	public UserRoleBean provisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleBean deProvisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleBean> findAllRoles() {
		List<UserRoleBean> userRoleBeanList = new ArrayList<>();
		
		List<UserRole> userRolesList = roleRepo.findAll();
		
		if(userRolesList != null && userRolesList.size() >0) {
			userRolesList.stream().forEach(userRoleDomain->{
				userRoleBeanList.add(mapDomainToBean(userRoleDomain));
			});
		}
		return userRoleBeanList;
	}
	
	/**
	 * converting bean to domain
	 * @param UserRoleBean
	 * @return
	 */
	@Override
	public UserRole mapBeanToDomain(UserRoleBean UserRoleBean) {
		UserRole roleDomain = new UserRole();
		BeanUtils.copyProperties(UserRoleBean, roleDomain);
		return roleDomain;
	}
   
	/**
	 * domain back to bean
	 * @param UserRoleBean
	 * @return
	 */
	@Override
	  public UserRoleBean mapDomainToBean(UserRole roleDomain) {
		  UserRoleBean userRoleBean = new UserRoleBean();
		  BeanUtils.copyProperties(roleDomain, userRoleBean);
		return userRoleBean;
	}

}
