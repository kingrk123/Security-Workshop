package com.medilab.preclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medilab.preclinic.idpclient.OutboundCommunicator;

public class MedilabUserAutnTest extends MedilabUserAuthnServiceApplicationTests {

	@Autowired
	private OutboundCommunicator ouboundCommn;
	
	String userName =  null;
	String password = null;
	
	@BeforeEach
	public void init() {
		userName = "Charles";
		password = "testUser@123";
	}
	
	@Test
	public void testIdpAuthn() {
		ouboundCommn.authenticateId(userName, password);
	}
}
