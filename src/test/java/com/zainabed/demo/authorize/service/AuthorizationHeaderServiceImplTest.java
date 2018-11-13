package com.zainabed.demo.authorize.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

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

import com.zainabed.demo.authorize.exception.BadRequestException;

@RunWith(SpringRunner.class)
public class AuthorizationHeaderServiceImplTest {

	@Autowired
	AuthorizationHeaderService service;

	@MockBean
	HttpServletRequest request;

	String type;
	String authHeaderType;
	String authHeader;

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
	}

	@Test
	public void shouldRetrunValidBearerHeader() {
		assertNotNull(service.getValue(request, authHeaderType));
	}

	@Test(expected = BadRequestException.class)
	public void shouldThrowExceptionForInvalidHeaderValue() {
		Mockito.when(request.getHeader(authHeader)).thenReturn("testvalue");
		service.getValue(request, authHeaderType);
	}

	@Test(expected = BadRequestException.class)
	public void shouldThrowExceptionForMissingAuthHeader() {
		Mockito.when(request.getHeader(authHeader)).thenReturn(null);
		service.getValue(request, authHeaderType);
	}
}
