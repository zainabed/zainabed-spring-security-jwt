package com.zainabed.spring.security.jwt.filter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
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

import com.zainabed.spring.security.jwt.exception.JwtAuthenticationException;
import com.zainabed.spring.security.jwt.security.JwtAuthenticationEntryPoint;
import com.zainabed.spring.security.jwt.service.AuthorizationHeaderService;
import com.zainabed.spring.security.jwt.service.JwtTokenService;
import com.zainabed.spring.security.jwt.service.UserDetailService;

import io.jsonwebtoken.Claims;

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
	JwtAuthenticationEntryPoint entryPoint;

	@MockBean
	Claims claims;

	@MockBean
	UsernamePasswordAuthenticationToken authentication;
	String token;

	@Autowired
	JwtAuthorizationFilter filter;

	@Configuration
	@Import(JwtAuthorizationFilter.class)
	static class Config {

	}

	@Before
	public void setup() throws JwtAuthenticationException {
		token = "testtoken";
		when(authHeaderService.getValue(any(), anyString())).thenReturn(token);
		when(userDetailSerivce.buildAuthentication(any())).thenReturn(authentication);
		when(jwtTokenService.parse(token)).thenReturn(claims);
	}

	@Test
	public void shouldProcessFilter() throws ServletException, IOException {
		filter.doFilter(request, response, filterChain);
		verify(filterChain).doFilter(request, response);
		verify(authHeaderService).getValue(any(), anyString());
		verify(userDetailSerivce).buildAuthentication(any());
		verify(jwtTokenService).parse(anyString());
	}

	public void shouldNotProcessTokenForInvalidToken() throws ServletException, IOException {
		when(authHeaderService.getValue(any(), anyString())).thenReturn(null);
		filter.doFilter(request, response, filterChain);
		verify(filterChain).doFilter(request, response);
		verify(authHeaderService, never()).getValue(any(), anyString());
		verify(userDetailSerivce, never()).buildAuthentication(any());
		verify(jwtTokenService, never()).parse(anyString());
	}
}
