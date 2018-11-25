package com.zainabed.spring.security.jwt.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

/**
 * Interface is design to generate {@link Authentication} object from JWT
 * {@link Claims} object. It will be used during authorization process where
 * filter is used to generate authentication object. and assign it to security
 * context.
 * 
 * @author Zainul Shaikh
 *
 */
@Component
public interface UserDetailService {

	/**
	 * Method generates {@link UsernamePasswordAuthenticationToken} object from
	 * given JWT claim object.
	 * 
	 * @param claim
	 *            JWT claim.
	 * @return UsernamePasswordAuthenticationToken object.
	 */
	UsernamePasswordAuthenticationToken buildAuthentication(Claims claim);

}
