/**
 * 
 */
package com.medilab.preclinic.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medilab.preclinic.bean.DoctorBean;
import com.medilab.preclinic.bean.MedilabUserBean;
import com.medilab.preclinic.bean.UserRoleBean;
import com.medilab.preclinic.bean.UserTypeBean;
import com.medilab.preclinic.model.Address;
import com.medilab.preclinic.model.Department;
import com.medilab.preclinic.model.Doctor;
import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.model.MedilabUserType;
import com.medilab.preclinic.model.UserRole;
import com.medilab.preclinic.repo.AddressRepo;
import com.medilab.preclinic.repo.DepartmentRepo;
import com.medilab.preclinic.repo.DoctorRepo;
import com.medilab.preclinic.repo.MedilabUserRepository;
import com.medilab.preclinic.util.RoleEnum;
import com.medilab.preclinic.util.ServiceUtil;

/**
 * @author nsanda
 *
 */
@Service
public class MedilabDoctorServiceImpl implements MedilabDoctorService {

	@Autowired
	private DoctorRepo doctRepo;

	@Autowired
	private DepartmentRepo deptRepo;

	@Autowired
	private AddressRepo addrRepo;

	@Autowired
	private MedilabUserRepository userRepo;
	
	@Autowired
	private UserRoleService roleService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#save(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	public DoctorBean save(DoctorBean doctBean) {
		Doctor doctModel = new Doctor();
		Address addrModel = new Address();
		BeanUtils.copyProperties(doctBean, addrModel);
		BeanUtils.copyProperties(doctBean, doctModel);
		doctModel.setPassword(pwdEncoder.encode(doctBean.getPassword()));

		doctModel.setAddress(addrModel);

		Department deptModel = deptRepo.findDeptByName(doctBean.getDept());
		doctModel.setDept(deptModel);

		doctRepo.save(doctModel);

		if (doctModel != null && doctModel.getDoctId() > 0) {
			userRepo.save(mapBeanToDomain(doctBean));
		}

		BeanUtils.copyProperties(doctModel, doctBean);

		BeanUtils.copyProperties(addrModel, doctBean);

		return doctBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#findAll()
	 */
	@Override
	@PreAuthorize("hasPermission('Doctor','VIEW')")
	public List<DoctorBean> findAll() {
		List<Doctor> doctModelList = doctRepo.findAll();
		List<DoctorBean> doctBeanList = new ArrayList<>();
		if (doctModelList != null && doctModelList.size() > 0) {
			doctModelList.stream().forEach(doctModel -> {
				DoctorBean doctBean = new DoctorBean();
				BeanUtils.copyProperties(doctModel, doctBean);
				if (doctModel.getAddress() != null) {
					BeanUtils.copyProperties(doctModel.getAddress(), doctBean);
				}

				doctBeanList.add(doctBean);
			});
		}
		return doctBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#findById(int)
	 */
	@Override
	public DoctorBean findById(int id) {
		Optional<Doctor> doctOpt = doctRepo.findById(id);
		DoctorBean doctBean = new DoctorBean();
		Doctor doctModel = doctOpt.get();
		BeanUtils.copyProperties(doctModel, doctBean);
		if (doctModel.getAddress() != null) {
			BeanUtils.copyProperties(doctModel.getAddress(), doctBean);
		}
		if (doctModel.getDept() != null) {
			doctBean.setDept(doctModel.getDept().getName());
		}

		return doctBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#findByName(java.lang.
	 * String)
	 */
	@Override
	public DoctorBean findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#delete(int)
	 */
	@Override
	@PreAuthorize("hasPermission('Doctor','DELETE')")
	public List<DoctorBean> delete(int id) {
		doctRepo.deleteById(id);
		return findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#delete(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	@PreAuthorize("hasPermission('Doctor','DELETE')")
	public List<DoctorBean> delete(DoctorBean deptBean) {
		Doctor Doctor = new Doctor();
		BeanUtils.copyProperties(deptBean, Doctor);
		doctRepo.delete(Doctor);
		return findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medilab.preclinic.service.MedilabDoctorService#update(com.medilab.
	 * preclinic.bean.DoctorBean)
	 */
	@Override
	@PreAuthorize("hasPermission('Doctor','MODIFY')")
	public DoctorBean update(DoctorBean doctBean) {
		Doctor doctModel = new Doctor();
		// Address addrModel = new Address();
		Address addrModel = addrRepo.findAddressByName(doctBean.getAddress());
		BeanUtils.copyProperties(doctBean, doctModel);

		BeanUtils.copyProperties(doctBean, addrModel);

		Department deptModel = deptRepo.findDeptByName(doctBean.getDept());
		doctModel.setDept(deptModel);
		doctModel.setAddress(addrModel);

		doctRepo.save(doctModel);
		doctBean.setDept(doctModel.getDept().getName());

		BeanUtils.copyProperties(doctModel, doctBean);
		BeanUtils.copyProperties(addrModel, doctBean);

		return doctBean;
	}

	private MedilabUser mapBeanToDomain(DoctorBean userBean) {

		MedilabUser userDomain = new MedilabUser();
		BeanUtils.copyProperties(userBean, userDomain);
		userDomain.setPassword(pwdEncoder.encode(userBean.getPassword()));
		String inputDob = userBean.getDob();
		try {
			userDomain.setDob(ServiceUtil.convertStringToDate(inputDob));
		} catch (ParseException e) {
			// Todo: convert to user definided exception later on time
			e.printStackTrace();
		}

		/**
		 * auto provisioning while creating user
		 */
		RoleEnum roleEnum = RoleEnum.getRoleByName(ServiceUtil.DOCTOR_ROLE);
		UserRoleBean userRoleBean = new UserRoleBean();
		userRoleBean.setId(roleEnum.getRoleId());
		userRoleBean.setName(roleEnum.getRole());
		if (userRoleBean != null) {
			UserRole roleDomain = roleService.mapBeanToDomain(userRoleBean);
			userDomain.setRole(roleDomain);
		}

		/*
		 * UserTypeBean userTypeBean = userBean.getUserType(); if (userTypeBean != null)
		 * { MedilabUserType userType = userTypeService.mapBeanToDomain(userTypeBean);
		 * userDomain.setUserType(userType); }
		 */

		return userDomain;
	}

}
