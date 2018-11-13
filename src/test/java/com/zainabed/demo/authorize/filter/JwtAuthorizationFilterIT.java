package com.zainabed.demo.authorize.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.demo.authorize.exception.BadRequestException;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtTokenService;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthorizationFilterIT {

	@Autowired
	JwtAuthorizationFilter filter;
	
	@Autowired
	JwtTokenService tokenService;
	
	@MockBean
	HttpServletRequest request;

	@MockBean
	HttpServletResponse response;

	@MockBean
	FilterChain filterChain;
	
	@Before
	public void setup(){
		String header = "Bearer " + tokenService.generate();
		Mockito.when(request.getHeader(AuthorizationHeaderService.AUTH_HEADER)).thenReturn(header);
	}
	
	@Test
	public void shoulCreateValidAuthenticationObject() throws ServletException, IOException{
		filter.doFilter(request, response, filterChain);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		assertNotNull(authentication.getName());
	}
	
	@Test(expected=BadRequestException.class)
	public void shouldThrowExceptionForInvalidAutHeader() throws ServletException, IOException{
		Mockito.when(request.getHeader(AuthorizationHeaderService.AUTH_HEADER)).thenReturn("test");
		filter.doFilter(request, response, filterChain);
	}
}
