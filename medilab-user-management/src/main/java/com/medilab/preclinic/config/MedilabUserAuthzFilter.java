package com.medilab.preclinic.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet Filter implementation class MedilabUserAuthzFilter
 */
//@Component
public class MedilabUserAuthzFilter extends OncePerRequestFilter {
    
	@Autowired
	private OutboundCommunicator outboundComm;
	/**
     * @see OncePerRequestFilter#OncePerRequestFilter()
     */
    public MedilabUserAuthzFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("**************************************");
		String headerValueWithBearer = request.getHeader("Authorization");
		if(headerValueWithBearer != null && headerValueWithBearer.startsWith("Bearer")) {
			String accessToken = headerValueWithBearer.replace("Bearer ", "");
			System.out.println("access token is:\t"+accessToken);
			if(accessToken != null) {
				List<GrantedAuthority> rolesList = new ArrayList<>();
				//central authentication api to get the user details from token
				String userResp = outboundComm.getUserInfo(accessToken);
				JSONObject jsonResp = new JSONObject(userResp);
				String loggedInUser = jsonResp.getString("sub");
				String authorities = jsonResp.getString("groups");
				if(authorities != null) {
					String[] rolesArray = authorities.split(",");
					for(String role : rolesArray) {
						rolesList.add(new SimpleGrantedAuthority(role));
					}
				}
				Authentication authnResp = new UsernamePasswordAuthenticationToken(loggedInUser, null,rolesList);
				SecurityContextHolder.getContext().setAuthentication(authnResp);
				filterChain.doFilter(request, response);
			}else {
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}else {
			response.sendError(HttpStatus.FORBIDDEN.value());
		}
		
	}

}
