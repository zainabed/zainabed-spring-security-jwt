package com.zainabed.demo.authorize.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.demo.authorize.entity.UserCredential;
import com.zainabed.demo.authorize.util.TokenUtil;

@RunWith(SpringRunner.class)
public class AuthorizationHeaderServiceImplTest {

	@Autowired
	AuthorizationHeaderService service;

	@MockBean
	HttpServletRequest request;

	String type;
	String authHeaderType;
	String authHeader;
	String username;
	String password;
	String token;
	UserCredential userCredentail;

	@Configuration
	@Import(AuthorizationHeaderServiceImpl.class)
	public static class Config {

	}

	@Before
	public void setup() {
		type = AuthorizationHeaderService.AUTH_TYPE_BEARER;
		authHeader = AuthorizationHeaderService.AUTH_HEADER;
		authHeaderType = "Bearer testvalue";
		Mockito.when(request.getHeader(authHeader)).thenReturn(authHeaderType);

		// Build token
		username = "testusername";
		password = "testpassword";
		token = AuthorizationHeaderService.AUTH_TYPE_BASIC +  TokenUtil.buildBasicToken(username, password);
	}

	@Test
	public void shouldRetrunValidBearerHeader() {
		assertNotNull(service.getValue(request, authHeaderType));
	}

	@Test
	public void shouldReturnNullForInvalidHeaderValue() {
		Mockito.when(request.getHeader(authHeader)).thenReturn("testvalue");
		assertNull(service.getValue(request, authHeaderType));
	}

	@Test
	public void shouldReturnNullForMissingAuthHeader() {
		Mockito.when(request.getHeader(authHeader)).thenReturn(null);
		assertNull(service.getValue(request, authHeaderType));
	}

	@Test
	public void shouldReturnUserCredentailFromToken() {
		Mockito.when(request.getHeader(AuthorizationHeaderService.AUTH_HEADER)).thenReturn(token);
		userCredentail = service.getBasicUserCredentials(request);
		assertEquals(userCredentail.getUsername(), username);
		assertEquals(userCredentail.getPassword(), password);
	}
}
