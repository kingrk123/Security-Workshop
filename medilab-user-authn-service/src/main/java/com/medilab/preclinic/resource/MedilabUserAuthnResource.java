package com.medilab.preclinic.resource;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medilab.preclinic.bean.MedilabUser;
import com.medilab.preclinic.service.MedilabUserAuthnService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*",
       methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.PATCH},
       origins = {"http://localhost:3000"},
       allowCredentials = "true",
       maxAge = 3600l
   )
public class MedilabUserAuthnResource {

	@Autowired
	private MedilabUserAuthnService iamAuthnService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@PostMapping("/token")
	public String authenticateUser(@RequestBody MedilabUser medilabUser) {
		String userAuthnResp = iamAuthnService.authenticate(medilabUser.getUserName(), medilabUser.getPassword());
		JSONObject json = new JSONObject(userAuthnResp);
		String accessToken = json.getString("access_token");
		System.out.println("access token in authn service is:\t"+accessToken);
		String refreshToken = json.getString("refresh_token");
		System.out.println("refresh token in authn service is:\t"+refreshToken);
		
		HashOperations hashMap = redisTemplate.opsForHash();
		hashMap.put("access_token"+medilabUser.getUserName(), "Bearer", accessToken);
		hashMap.put("refresh_token"+medilabUser.getUserName(), "Bearer", refreshToken);
		return json.toString();
	}
	
	@GetMapping("/token")
	public String getUserInfo(HttpServletRequest request) {
		String headerValueWithBearer = request.getHeader("Authorization");
		if(headerValueWithBearer != null && headerValueWithBearer.startsWith("Bearer")) {
			String accessToken = headerValueWithBearer.replace("Bearer ", "");
			System.out.println("access token is:\t"+accessToken);
			return iamAuthnService.viewUserProfile(accessToken);
		}
		return headerValueWithBearer;
		
	}
}
