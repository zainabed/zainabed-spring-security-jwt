package com.zainabed.spring.security.jwt.service;

import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.AuthenticationToken;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.exception.JwtAuthenticatioException;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public interface JwtTokenService {

	/**
	 * 
	 * @param token
	 * @return
	 * @throws JwtAuthenticatioException
	 */
	Claims parse(String token) throws JwtAuthenticatioException;

	/**
	 * 
	 * @param userDetail
	 * @return
	 */
	String build(UserDetail userDetail);

	/**
	 * 
	 * @param userDetail
	 * @return
	 */
	AuthenticationToken getToken(UserDetail userDetail);

	/**
	 * 
	 * @return
	 */
	String generate();

}
