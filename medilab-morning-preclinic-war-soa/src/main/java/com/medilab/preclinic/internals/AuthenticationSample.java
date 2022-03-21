package com.medilab.preclinic.internals;

import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class AuthenticationSample {
	private static AuthenticationManager authenticationManager = new CustomAuthenticationProvider();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Username: ");
		String name = sc.nextLine();
		System.out.println("Please enter Password: ");
		String password = sc.nextLine();

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(name, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}
}

class CustomAuthenticationProvider implements AuthenticationManager {

	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
		manager.createUser(User.withUsername("user").password("user").roles("USER").build());
		return manager;
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
	
		UserDetails user=userDetailsService().loadUserByUsername(auth.getName());
		if(user !=null){
			if(user.getPassword().equals(auth.getCredentials())){
				if(user.getUsername().equals("admin")){
				return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), 
						new ArrayList<GrantedAuthority>(){
							{
								add(new SimpleGrantedAuthority("ROLE_ADMIN"));
							}
							});
				}
				else{
					System.out.println("User is"+user.getUsername());
					return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), 
							new ArrayList<GrantedAuthority>(){
							{
								add(new SimpleGrantedAuthority("ROLE_USER"));
							}
							});
				}
			}
		}
		
		throw new BadCredentialsException("Bad Credentials");
		
	}
}
