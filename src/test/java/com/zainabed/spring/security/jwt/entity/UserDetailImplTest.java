package com.zainabed.spring.security.jwt.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.entity.UserDetailImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RunWith(SpringRunner.class)
public class UserDetailImplTest {

	@Autowired
	UserDetail userDetail;

	@MockBean
	Claims claims;

	String username;
	List<String> roles;

	@Configuration
	@Import(UserDetailImpl.class)
	public static class Config {

	}

	@Before
	public void setup() {
		username = "testusername";
		roles = new ArrayList<String>();
		roles.add("ADMIN");
		Mockito.when(claims.getSubject()).thenReturn(username);
		Mockito.when(claims.get("roles")).thenReturn(roles);
	}

	@Test
	public void shouldBuildUserDetailFromClaims() {
		userDetail.build(claims);
		assertEquals(username, userDetail.getUsername());
		assertNotNull(userDetail.getRoles());
	}

}
