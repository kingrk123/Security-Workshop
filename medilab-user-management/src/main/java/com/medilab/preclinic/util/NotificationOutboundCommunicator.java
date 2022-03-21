package com.medilab.preclinic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationOutboundCommunicator {

	@Autowired
	private RestTemplate rt;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	public String sendConfirmAccountEmail(String notificationData) {
		//RestTemplate rt = new RestTemplate();
		
		String loggedinUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		HashOperations hashMap = redisTemplate.opsForHash();
		String accessToken = String.valueOf(hashMap.get("access_token"+loggedinUser, "Bearer"));
		System.out.println("access token in user management service is:\t"+accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Bearer "+accessToken);
		
		HttpEntity<String> entity = new HttpEntity<String>(notificationData, headers);
		
		ResponseEntity<String> respJson = rt.postForEntity(ServiceUtil.NOTIFICATION_SERVICE_URI, entity, String.class);
		
		System.out.println(respJson.getBody());
		return respJson.getBody();
	}
	
}
