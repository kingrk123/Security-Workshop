package com.medilab.preclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MedilabUserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private MedilabUserDetailsService medilabUserDetails;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		System.out.println("logged in user Name is:\t"+userName);
		Authentication authnResp = null;
		UserDetails userDetails = medilabUserDetails.loadUserByUsername(userName);
		if(userDetails != null) {
			boolean isPwdMatching = pwdEncoder.matches(String.valueOf(authentication.getCredentials()), userDetails.getPassword());
			System.out.println("does authentication success?:\t"+isPwdMatching);
			if(isPwdMatching) {
				authnResp = new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());
			}
		}
		
		return authnResp;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
