package com.medilab.notification.resource;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.medilab.notification.bean.NotificationBean;
import com.medilab.notification.util.EmailTemplates;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0")
@Slf4j
public class NotificationResource {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@PostMapping("/notifications")
	public NotificationBean sendEmailNotification(@RequestBody NotificationBean notification) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setFrom(notification.getFrom());
			helper.setTo(notification.getTo());
            if(notification.getBcc() !=  null && notification.getBcc().length >0) {
            	helper.setBcc(notification.getBcc());
            }
            String emailTemplateName = EmailTemplates.getTemplateName(notification.getEmailTemplateType());
            
            for(String to : notification.getTo()) {
            	// Prepare the evaluation context
                final Context ctx = new Context(Locale.ENGLISH);
                ctx.setVariable("name", to);
                ctx.setVariable("greeting", "Hello");
                String callBackUrl = notification.getCallbackUrl();
                String code = notification.getUserVerifyCode();
                callBackUrl = callBackUrl+"/"+code;
                
                ctx.setVariable("callBackUrl", callBackUrl);
                // Create the HTML body using Thymeleaf
                final String htmlContent = this.templateEngine.process(emailTemplateName, ctx);
                helper.setText(htmlContent, true); // true = isHtml
                
             // Send mail
                this.mailSender.send(helper.getMimeMessage());
            }

        
            
            
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notification;
	}
	
}
