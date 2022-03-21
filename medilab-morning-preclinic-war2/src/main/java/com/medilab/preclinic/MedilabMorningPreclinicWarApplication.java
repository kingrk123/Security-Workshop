package com.medilab.preclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterChainProxy;

@SpringBootApplication
//@EnableWebSecurity(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MedilabMorningPreclinicWarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilabMorningPreclinicWarApplication.class, args);
		
	}
	

}
