package com.medilab.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class NotificationSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .authorizeRequests() .anyRequest() .authenticated() .and() .httpBasic()
		 * .and() .addFilterBefore(medilabUserAuthzFilter(),
		 * BasicAuthenticationFilter.class);
		 */
		http
		.authorizeRequests()
		//.antMatchers(HttpMethod.POST,"/api/v1.0/notifications")
		.anyRequest()
		.authenticated()
		//.permitAll()
		//.anyRequest().authenticated()
		//.antMatchers("/api/users").permitAll()
		.and().csrf().disable()
		.addFilterBefore(medilabUserAuthzFilter(), BasicAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		//.formLogin().disable()
		.formLogin().and()
		.httpBasic();
	}
	
	@Bean
	public MedilabUserAuthzFilter medilabUserAuthzFilter() {
		
		return new MedilabUserAuthzFilter();
		
	}
}
