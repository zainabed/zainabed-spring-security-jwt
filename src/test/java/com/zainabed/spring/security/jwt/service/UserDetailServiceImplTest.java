package com.zainabed.spring.security.jwt.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.service.UserDetailService;
import com.zainabed.spring.security.jwt.service.UserDetailServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RunWith(SpringRunner.class)
public class UserDetailServiceImplTest {

	@Autowired
	UserDetailService service;
	
	@MockBean
	UserDetail userDetail;

	@MockBean
	Claims claim;

	UsernamePasswordAuthenticationToken authenticationToken;

	@Configuration
	@Import(UserDetailServiceImpl.class)
	public static class Config {

	}

	@Before
	public void setup() {

	}

	@Test
	public void shouldReturnValidAuthneticationObject() {
		authenticationToken = service.buildAuthentication(claim);
		assertNotNull(authenticationToken);
	}

}
