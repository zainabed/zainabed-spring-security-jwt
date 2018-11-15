package com.zainabed.demo.authorize.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zainabed.demo.authorize.entity.AuthenticationToken;
import com.zainabed.demo.authorize.entity.UserCredential;
import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.service.AuthorizationHeaderService;
import com.zainabed.demo.authorize.service.JwtAuthenticationService;
import com.zainabed.demo.authorize.service.JwtTokenService;

@RestController
@RequestMapping(value = "/auth")
public class JwtAuthenticationController {

	@Autowired
	AuthorizationHeaderService authHeaderService;

	@Autowired
	JwtAuthenticationService jwtAuthenticationService;

	@Autowired
	JwtTokenService jwtTokenService;

	@RequestMapping(method = RequestMethod.POST)
	public AuthenticationToken testUser(HttpServletRequest request) {
		UserCredential userCredential = authHeaderService.getBasicUserCredentials(request);
		UserDetail userDetail = jwtAuthenticationService.authenticate(userCredential);
		return jwtTokenService.getToken(userDetail);
	}
}
