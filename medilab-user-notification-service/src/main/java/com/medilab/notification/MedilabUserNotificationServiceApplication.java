package com.medilab.notification;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.medilab.notification.util.NotificationConstants;

@SpringBootApplication
public class MedilabUserNotificationServiceApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(MedilabUserNotificationServiceApplication.class, args);
	}

	@Bean
	public JavaMailSender mailSender() throws IOException {

		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Basic mail sender configuration, based on emailconfig.properties
		mailSender.setHost(this.environment.getProperty(NotificationConstants.EMAIL_HOST_NAME));
		mailSender.setPort(Integer.parseInt(this.environment.getProperty(NotificationConstants.EMAIL_PORT)));
		//mailSender.setProtocol(this.environment.getProperty(NotificationConstants.EMAIL_PROTOCOL));
		mailSender.setUsername(this.environment.getProperty(NotificationConstants.EMAIL_USER_NAME));
		mailSender.setPassword(this.environment.getProperty(NotificationConstants.EMAIL_PASSWORD));

		return mailSender;

	}

	@Bean
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(Integer.valueOf(2));
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("classpath:/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(NotificationConstants.EMAIL_TEMPLATE_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}
}
