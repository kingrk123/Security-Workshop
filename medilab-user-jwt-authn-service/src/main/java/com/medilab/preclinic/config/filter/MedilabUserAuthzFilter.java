package com.medilab.preclinic.config.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medilab.preclinic.config.JwtTokenService;
import com.medilab.preclinic.util.ServiceUtil;

/**
 * Servlet Filter implementation class MedilabUserAuthzFilter
 */
//@WebFilter("/MedilabUserAuthzFilter")
public class MedilabUserAuthzFilter extends OncePerRequestFilter {
    
	@Autowired
	private JwtTokenService jwtTokenService;
	
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
		String tokenWithBearer = request.getHeader(ServiceUtil.JWT_TOKEN_HEDER_NAME);
		if(tokenWithBearer != null && tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
			UserDetails userDetails = jwtTokenService.validateToken(tokenWithBearer);
			if(userDetails != null) {
				Authentication authnResp = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authnResp);
				filterChain.doFilter(request, response);
			}else {
				JSONObject jsonBuilder = new JSONObject();
				jsonBuilder.put("error_msg", "Token is invalid. please get the new token");
				throw new BadCredentialsException(jsonBuilder.toString());
			}
		}else {
			filterChain.doFilter(request, response);
		}
		
		
	}

}
