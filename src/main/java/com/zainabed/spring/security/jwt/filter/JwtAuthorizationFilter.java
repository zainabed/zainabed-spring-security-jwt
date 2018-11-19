package com.zainabed.spring.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zainabed.spring.security.jwt.service.AuthorizationHeaderService;
import com.zainabed.spring.security.jwt.service.JwtTokenService;
import com.zainabed.spring.security.jwt.service.UserDetailService;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter  {

	@Autowired
	AuthorizationHeaderService authHeaderService;

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	JwtTokenService jwtTokenService;

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = authHeaderService.getValue(request, AuthorizationHeaderService.AUTH_TYPE_BEARER);
		if (token != null) {
			Claims claims = jwtTokenService.parse(token);
			UsernamePasswordAuthenticationToken authentication = userDetailService.buildAuthentication(claims);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
		
	}

}
