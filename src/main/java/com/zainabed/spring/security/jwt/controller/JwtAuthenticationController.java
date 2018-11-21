package com.zainabed.spring.security.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zainabed.spring.security.jwt.entity.AuthenticationToken;
import com.zainabed.spring.security.jwt.entity.UserCredential;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.service.AuthorizationHeaderService;
import com.zainabed.spring.security.jwt.service.JwtAuthenticationService;
import com.zainabed.spring.security.jwt.service.JwtTokenService;

@RestController
@RequestMapping(value = "/auth")
@ConditionalOnProperty(prefix = "jwt", name = "authentication")
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
