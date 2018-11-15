package com.zainabed.demo.authorize.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.demo.authorize.entity.AuthenticationToken;
import com.zainabed.demo.authorize.entity.UserDetail;

import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
// @SpringBootTest
@TestPropertySource(properties = { "jwt.token.secret=dksjflkdsjfkldjsklfjdslkjflkdsjfkldsjk",
		"jwt.token.expiration=900000" })
public class JwtTokenServiceImplTest {

	@Autowired
	JwtTokenService jwtTokenService;

	@Configuration
	@Import(JwtTokenServiceImpl.class)
	public static class Config {

	}

	String token;
	Claims claims;

	@MockBean
	UserDetail userDetail;
	String username;
	List<String> roles;
	AuthenticationToken authToken;

	@Before
	public void setup() {
		username = "testusername";
		roles = new ArrayList<String>();
		roles.add("ADMIN");
		Mockito.when(userDetail.getUsername()).thenReturn(username);
		Mockito.when(userDetail.getRoles()).thenReturn(roles);
	}

	@BeforeClass
	public static void beforeClass() {
		// org.springframework.test.util.ReflectionTestUtils.setField(JwtTokenServiceImplTest.class,
		// "jwt.token.secret", "fjdsklfjdslkfjdsklfdsfdsfdsf");
	}

	@Test
	public void shouldReturnValidJWTtoken() {
		token = jwtTokenService.build(userDetail);
		assertTrue(token.matches("[A-Za-z0-9\\-\\._~\\+\\/]+=*"));
	}

	@Test
	public void shouldPraseTokenString() {
		token = jwtTokenService.build(userDetail);
		claims = jwtTokenService.parse(token);
		assertNotNull(claims);
	}

	@Test
	public void shouldReturnValidAuthenticationToken() {
		authToken = jwtTokenService.getToken(userDetail);
		assertNotNull(authToken.getToken());
		assertEquals(authToken.getType(), AuthorizationHeaderService.AUTH_TYPE_BEARER);
	}
}
