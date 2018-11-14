package com.zainabed.demo.authorize.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class JwtAuthenticationController {

	@RequestMapping(method = RequestMethod.POST)
	public String testUser() {
		return "Test GET controller.";
	}
}
