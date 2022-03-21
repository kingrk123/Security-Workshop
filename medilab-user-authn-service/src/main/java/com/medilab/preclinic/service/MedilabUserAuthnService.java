package com.medilab.preclinic.service;

public interface MedilabUserAuthnService {

	public String authenticate(String userName, String password);
	public String renewToken(String refreshToken, String accessTokenToBeRenew);
	public String validateToken(String accessToken);
	public String viewUserProfile(String accessToken);
}
