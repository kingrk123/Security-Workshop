package com.medilab.preclinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceUtil {

	private static final String UI_USER_DOB_DATE_FORMAT = "dd/MM/yyyy";
	public static final String NOTIFICATION_SERVICE_URI = "http://localhost:5011/api/v1.0/notifications";
	
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
