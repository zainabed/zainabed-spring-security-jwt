package com.zainabed.demo.authorize.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zainabed.demo.authorize.exception.BadRequestException;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtTokenService;
import com.zainabed.demo.authorize.service.UserDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	AuthorizationHeaderService authHeaderService;

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	JwtTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException , BadRequestException{

		String token = authHeaderService.getValue(request, AuthorizationHeaderService.AUTH_TYPE_BEARER);
		System.out.println("------------ Token---: " + token);
		Claims claims = jwtTokenService.parse(token);
		UsernamePasswordAuthenticationToken authentication = userDetailService.buildAuthentication(claims);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		filterChain.doFilter(request, response);

	}

}
