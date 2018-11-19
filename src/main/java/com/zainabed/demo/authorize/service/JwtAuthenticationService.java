package com.zainabed.demo.authorize.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.UserCredential;
import com.zainabed.demo.authorize.entity.UserDetail;

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
