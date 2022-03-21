package com.medilab.preclinic.resource;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.repo.MedilabUserRepository;

@RestController
public class LoginController {

	@Autowired
	private MedilabUserRepository userRepository;
	
	@RequestMapping("/login")
	public MedilabUser getUserDetailsAfterLogin(Authentication auth) {
		MedilabUser customers = userRepository.findUserByEmail(auth.getName());
		return customers;
		
	}

}
