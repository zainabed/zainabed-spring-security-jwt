package com.zainabed.spring.security.jwt.controller;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class AuthSecuirtyControlller {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String testUser() {
		return "Test GET controller.";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/user/role", method = RequestMethod.GET)
	public String testUserWithRole() {
		return "Test GET controller.";
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String testAdmin() {
		return "Test GET controller.";
	}
}
