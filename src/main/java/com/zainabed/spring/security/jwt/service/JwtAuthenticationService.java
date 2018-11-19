package com.zainabed.spring.security.jwt.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.UserCredential;
import com.zainabed.spring.security.jwt.entity.UserDetail;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public interface JwtAuthenticationService {

	/**
	 * 
	 * @param userCredential
	 * @return
	 * @throws AuthenticationException
	 */
	UserDetail authenticate(UserCredential userCredential) throws AuthenticationException;
}
