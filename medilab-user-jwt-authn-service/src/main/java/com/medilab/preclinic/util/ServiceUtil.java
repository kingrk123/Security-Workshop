package com.medilab.preclinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ServiceUtil {

	private static final String UI_USER_DOB_DATE_FORMAT = "dd/MM/yyyy";
	public static final String DOCTOR_ROLE = "Doctor";
	public static final String ADMIN_ROLE = "Admin";
	
	/**
	 * in milli seconds
	 */
	public static final long JWT_TOKEN_EXPIRY=1800000L;
	public static final String JWT_TOKEN_API_KEY=new String(Base64.getEncoder().encode("medilab-app".getBytes()));
	public static final String JWT_TOKEN_ISSUER="medilab-app";
	public static final String JWT_TOKEN_HEDER_NAME="Authorization";
	public static final String JWT_TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_TYPE = "Bearer";
	
	public static Date convertStringToDate(String uiDob) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(UI_USER_DOB_DATE_FORMAT);
		return sdf.parse(uiDob);
	}
	
	public static void main(String[] args) {
		try {
			Date date = convertStringToDate("11/09/2021");
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
