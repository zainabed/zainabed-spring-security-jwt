package com.zainabed.demo.authorize.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zainabed.demo.authorize.DemoAuthorizeApplication;
import com.zainabed.demo.authorize.controller.security.TestJwtWebSecurity;
import com.zainabed.demo.authorize.entity.UserDetailImpl;
import com.zainabed.demo.authorize.exception.BadRequestException;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtTokenService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoAuthorizeApplication.class, AuthSecuirtyControlller.class , TestJwtWebSecurity.class})
@AutoConfigureMockMvc
public class AuthSecuirtyControlllerIT {

	@Autowired
	MockMvc mvc;

	@Autowired
	JwtTokenService service;

	String token;

	@Autowired
	UserDetailImpl userDetail;
	List<String> roles;

	@Before
	public void setup() {
		roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		userDetail.setUsername("testuser");
		userDetail.setRoles(roles);
		token = AuthorizationHeaderService.AUTH_TYPE_BEARER + service.build(userDetail);
	}

	@Test
	public void shouldWorkForTestUserPathWithValidHeader() throws Exception {
		mvc.perform(get("/test/user").header(AuthorizationHeaderService.AUTH_HEADER, token)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldWorkForTestUserPathWithValidHeaderAndRole() throws Exception {
		mvc.perform(get("/test/user/role").header(AuthorizationHeaderService.AUTH_HEADER, token)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldThrowUnAuthErrorForTestUserPath() throws Exception {
		mvc.perform(get("/test/user")).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test
	public void shouldThrowUnAuthErrorForTestAdminPath() throws Exception {
		mvc.perform(get("/test/admin").header(AuthorizationHeaderService.AUTH_HEADER, token)).andDo(print())
				.andExpect(status().is5xxServerError());
	}
}
