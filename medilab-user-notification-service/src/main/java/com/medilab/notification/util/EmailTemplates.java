/**
 * 
 */
package com.medilab.notification.util;

/**
 * @author IM-LP-1763
 *
 */
public enum EmailTemplates {

	ACCOUNT_CONFIRMATION("AccountConfirmation","confirmAccount.html"),
	ACCOUNT_ENABLE("AccountEnable",""),
	PASSWORD_RESET("PasswordReset","passwordReset.html");
	
	private String templateType;
	private String templateName;
	
	EmailTemplates(String templateType,String templateName){
		this.templateType=templateType;
		this.templateName=templateName;
	}
	
	public static String getTemplateName(String templateType) {
		for(EmailTemplates emailTemplate : EmailTemplates.values()) {
			if(emailTemplate.templateType.equals(templateType)) {
				return emailTemplate.templateName;
			}
		}
		return null;
	}
}
