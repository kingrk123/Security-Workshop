package com.medilab.preclinic.config;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.medilab.preclinic.util.ServiceUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {

	@Autowired
	private MedilabUserDetailsService userDetailsService;
	
	public String createToken(String loggedInUserName, List<String> roles) {
		
		Claims identities = Jwts.claims();
		
		identities.setSubject(loggedInUserName);
		identities.setIssuer(ServiceUtil.JWT_TOKEN_ISSUER);
		Date currentServerTime = new Date();
		identities.setIssuedAt(currentServerTime);
		identities.setExpiration(new Date(currentServerTime.getTime()+ServiceUtil.JWT_TOKEN_EXPIRY));
		
		identities.put("USER_ROLES", roles);
		
		
		
		return Jwts
		.builder()
		.setClaims(identities)
		.signWith(SignatureAlgorithm.HS256, ServiceUtil.JWT_TOKEN_API_KEY)
		.compact();
		
	}
	
	public UserDetails validateToken(String tokenWithBearer) {
		boolean isTokenValid = false;
		
		String tokenVal = resolveToken(tokenWithBearer);
		if(tokenVal != null) {
			Claims userIdentities = Jwts.parser().setSigningKey(ServiceUtil.JWT_TOKEN_API_KEY).parseClaimsJws(tokenVal).getBody();
			String loggedInUser = userIdentities.getSubject();
			UserDetails userDetails = userDetailsService.loadUserByUsername(loggedInUser);
			if(userDetails != null) {
				//
				Date tokenExpDate = userIdentities.getExpiration();
				isTokenValid = new Date().before(tokenExpDate);
				if(isTokenValid) {
					return userDetails;
				}
			}
			
		}
		
		return null;
	}
	
	private String resolveToken(String tokenWithBearer) {
		String tokenVal = null;
		if(tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
			tokenVal = tokenWithBearer.replace(ServiceUtil.JWT_TOKEN_PREFIX, "");
			
		}
		return tokenVal;
	}
}
