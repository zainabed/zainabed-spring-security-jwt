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

/**
 * This controller performs Basic authentication.
 * 
 * <p>
 * It is part of Jwt Security module and by default it is turned off. Enable it
 * by adding following property with value true
 * </p>
 *
 * <pre class="code">
 * jwt.authentication = true
 * </pre>
 * 
 * Controller requires Basic Authentication realm. it should be passed user
 * credentials like username and password into Authorization header with value
 * merged with character : and encoded with Base64 encoding and prefixed by
 * "Basic " realm.
 * 
 * <pre class="code">
 *  Authorization: Basic "Base64 encoded username and password"
 * </pre>
 * 
 * Controller let you to implement user authentication mechanism, to do so you
 * have to implement {@code @JwtAuthenticationService} service and annotated
 * with {@code @Service}.
 * 
 * @author Shaikh Zainul
 *
 */
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
	public AuthenticationToken authenticate(HttpServletRequest request) {
		UserCredential userCredential = authHeaderService.getBasicUserCredentials(request);
		UserDetail userDetail = jwtAuthenticationService.authenticate(userCredential);
		return jwtTokenService.getToken(userDetail);
	}
}
