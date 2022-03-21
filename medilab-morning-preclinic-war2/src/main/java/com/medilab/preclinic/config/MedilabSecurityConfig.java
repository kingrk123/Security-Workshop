/**
 * 
 */
package com.medilab.preclinic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * @author IM-LP-1763
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MedilabSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MedilabUserAuthenticationProvider medilabAuthnProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http
		 .addFilterBefore(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		 .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/dashboard").authenticated()
		.antMatchers("/doctors").hasRole("Doctor")
		.antMatchers("/api/*").authenticated().and()
		.formLogin().and()
		.httpBasic();
		
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("admin").authorities
		 * ("admin").and().withUser("user") .password("user").authorities("user");
		 * 
		 * auth.jdbcAuthentication().dataSource(dataSource);
		 */
		auth.jdbcAuthentication().dataSource(dataSource);
		auth.authenticationProvider(medilabAuthnProvider);
		
	}
	 

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * InMemoryUserDetailsManager inMemoryManager = new
	 * InMemoryUserDetailsManager(); UserDetails user1 = new User("admin", "admin",
	 * Arrays.asList(new SimpleGrantedAuthority("admin"))); UserDetails user2 = new
	 * User("user", "user", Arrays.asList(new SimpleGrantedAuthority("user")));
	 * 
	 * inMemoryManager.createUser(user1); inMemoryManager.createUser(user2);
	 * 
	 * return inMemoryManager;
	 * 
	 * JdbcUserDetailsManager jdbcUserDetialManager = new
	 * JdbcUserDetailsManager(dataSource); return jdbcUserDetialManager;
	 * 
	 * }
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(5);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// super.configure(web);
		web.ignoring().antMatchers("/assests/**");

	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder(5).encode("12345"));
	}

}
