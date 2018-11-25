package com.zainabed.spring.security.jwt.service;

import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.AuthenticationToken;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.exception.JwtAuthenticationException;

import io.jsonwebtoken.Claims;

/**
 * This service is designed to perform JWT related operations. Basically it
 * generates access {@link AuthenticationToken} object and parse access token to
 * validate it.
 * 
 * @author Zainul Shaikh
 *
 */
@Component
public interface JwtTokenService {

	/**
	 * Validate access token, if access token if invalid then it throws
	 * {@link JwtAuthenticationException}.
	 * 
	 * @param token Access token.
	 * @return JWT claim object.
	 * @throws JwtAuthenticationException Authentication exception
	 */
	Claims parse(String token) throws JwtAuthenticationException;

	/**
	 * Generates JWT token from {@link UserDetail} object.
	 * 
	 * @param userDetail UserDetail object
	 * @return JWT token
	 */
	String build(UserDetail userDetail);

	/**
	 * Get {@link AuthenticationToken} from {@link UserDetail} object.
	 * 
	 * @param userDetail UserDetail object
	 * @return JWT token
	 */
	AuthenticationToken getToken(UserDetail userDetail);

	/**
	 * Generates {@link AuthenticationToken} from refresh token.
	 * 
	 * @param refreshToken
	 * @return Authentication object
	 */
	AuthenticationToken getToken(String refreshToken);

	String generate();

}
