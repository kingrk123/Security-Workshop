/**
 * 
 */
package com.medilab.preclinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author IM-LP-1763
 *
 */
@Configuration
public class MedilabSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/dashboard").authenticated()
		.antMatchers("/doctor").authenticated()
		.antMatchers("/api/*").authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}
	
}
