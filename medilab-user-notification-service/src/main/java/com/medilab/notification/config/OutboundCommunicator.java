package com.medilab.notification.config;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@Component
public class OutboundCommunicator {

	@Autowired
	private RestTemplate rt;

	
	@Value("${medilab.authn.userinfo.endpoint}")
	private String userEndpoint;

	
	public String getUserInfo(String accessToken) {
		System.out.println("access token going to idp is:\t" + accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		// headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		HttpEntity requestData = new HttpEntity(headers);
		ignoreCertificates();
		//ResponseEntity<String> iamResp = rt.getForEntity(userEndpoint, String.class, requestData);
		ResponseEntity<String> iamResp = rt.exchange(userEndpoint, HttpMethod.GET, requestData, String.class);
		if (iamResp.getStatusCodeValue() == HttpStatus.OK.value()) {
			System.out.println(iamResp.getBody());
			String respBody = iamResp.getBody();

			JSONObject jsonRespBody = new JSONObject(respBody);
			// String accessToken = jsonRespBody.getString("acces_token");

			return jsonRespBody.toString();
		}

		return null;
	}

	private void ignoreCertificates() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {

		}
	}
}
