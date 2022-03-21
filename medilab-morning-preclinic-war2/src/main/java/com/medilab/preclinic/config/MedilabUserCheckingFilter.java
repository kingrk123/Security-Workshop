package com.medilab.preclinic.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet Filter implementation class MedilabUserCheckingFilter
 */
//@WebFilter("/MedilabUserCheckingFilter")
public class MedilabUserCheckingFilter extends OncePerRequestFilter {
       
    /**
     * @see OncePerRequestFilter#OncePerRequestFilter()
     */
    public MedilabUserCheckingFilter() {
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
		System.out.println("i am from MedilabUserCheckingFilter");
		System.out.println("user name is:\t"+request.getParameter("name"));
		System.out.println("password is:\t"+request.getParameter("password"));
		
		System.out.println(request.getHeader("Authorization"));
		
		filterChain.doFilter(request, response);
	}

}
