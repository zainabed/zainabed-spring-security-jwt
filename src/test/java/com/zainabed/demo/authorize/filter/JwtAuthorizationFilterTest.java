package com.zainabed.demo.authorize.filter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.demo.authorize.exception.BadRequestException;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtTokenService;
import com.zainabed.demo.authorize.service.UserDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RunWith(SpringRunner.class)
public class JwtAuthorizationFilterTest {

	@MockBean
	AuthorizationHeaderService authHeaderService;

	@MockBean
	UserDetailService userDetailSerivce;

	@MockBean
	JwtTokenService jwtTokenService;

	@MockBean
	HttpServletRequest request;

	@MockBean
	HttpServletResponse response;

	@MockBean
	FilterChain filterChain;

	@MockBean
	Claims claims;

	@MockBean
	UsernamePasswordAuthenticationToken authentication;
	String token;
	
	@Autowired
	JwtAuthorizationFilter filter;
	
	@Configuration
	@Import(JwtAuthorizationFilter.class)
	static class Config{
		
	}

	@Before
	public void setup() throws BadRequestException {
		token = "testtoken";
		when(authHeaderService.getValue(any(), anyString()))
				.thenReturn(token);
		when(userDetailSerivce.buildAuthentication(any())).thenReturn(authentication);
		when(jwtTokenService.parse(token)).thenReturn(claims);
	}

	@Test
	public void shouldProcessFilter() throws ServletException, IOException {
		filter.doFilter(request, response, filterChain);
		verify(filterChain).doFilter(request, response);
	}
	
	@Test(expected=BadRequestException.class)
	public void shouldThrowBadRequestErrorForInvalidToken() throws ServletException, IOException{
		when(jwtTokenService.parse(token)).thenThrow(BadRequestException.class);
		filter.doFilter(request, response, filterChain);
	}
}
