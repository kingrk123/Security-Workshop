package com.medilab.preclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilab.preclinic.idpclient.OutboundCommunicator;

@Service
public class MedilabUserAuthnServiceImpl implements MedilabUserAuthnService {

	@Autowired
	private OutboundCommunicator iamClient;
	
	@Override
	public String authenticate(String userName, String password) {
		return iamClient.authenticateId(userName, password);
	}

	@Override
	public String renewToken(String refreshToken, String accessTokenToBeRenew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewUserProfile(String accessToken) {
		return iamClient.getUserInfo(accessToken);
	}

}
