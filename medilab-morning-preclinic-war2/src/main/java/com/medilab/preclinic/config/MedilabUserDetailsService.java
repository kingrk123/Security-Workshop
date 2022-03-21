package com.medilab.preclinic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.model.UserRole;
import com.medilab.preclinic.repo.MedilabUserRepository;

@Component
public class MedilabUserDetailsService implements UserDetailsService {

	@Autowired
	MedilabUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MedilabUser databaseUser = userRepo.findUserByEmail(username);
		
		List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
		UserRole userRole = databaseUser.getRole();
		authoritiesList.add(new SimpleGrantedAuthority("ROLE_"+userRole.getName()));
		
		return new User(databaseUser.getEmail(), databaseUser.getPassword(), authoritiesList);
	}

}
