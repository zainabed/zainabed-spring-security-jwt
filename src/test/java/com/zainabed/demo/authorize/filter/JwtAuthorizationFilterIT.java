package com.zainabed.demo.authorize.filter;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.zainabed.demo.authorize.entity.UserDetailImpl;
import com.zainabed.demo.authorize.exception.BadRequestException;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtTokenService;
import com.zainabed.demo.authorize.service.UserDetailService;

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

	String token;
	List<String> roles;
	@Autowired
	UserDetailImpl userDetail;

	@Before
	public void setup() {
		roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		userDetail.setUsername("testuser");
		userDetail.setRoles(roles);
		token = AuthorizationHeaderService.AUTH_TYPE_BEARER + tokenService.build(userDetail);
		Mockito.when(request.getHeader(AuthorizationHeaderService.AUTH_HEADER)).thenReturn(token);
	}

	@Test
	public void shoulCreateValidAuthenticationObject() throws ServletException, IOException {
		filter.doFilter(request, response, filterChain);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		assertNotNull(authentication.getName());
	}

	@Test
	public void shouldNotExecuteJWTProcessForInvalidAutHeader() throws ServletException, IOException {
		Mockito.when(request.getHeader(AuthorizationHeaderService.AUTH_HEADER)).thenReturn("test");
		filter.doFilter(request, response, filterChain);
		verify(filterChain).doFilter(request, response);
		AuthorizationHeaderService authHeaderService = spy(filter.authHeaderService);
		UserDetailService userDetailSerivce = spy(filter.userDetailService);
		JwtTokenService jwtTokenService = spy(filter.jwtTokenService);
		verify(authHeaderService, never()).getValue(any(), anyString());
		verify(userDetailSerivce, never()).buildAuthentication(any());
		verify(jwtTokenService, never()).parse(anyString());
	}
}
