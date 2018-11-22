package com.zainabed.spring.security.jwt.entity;

/**
 * Interface is designed to capture user credentials as username and password
 * from Basic authentication request.
 * 
 * Interface is passed to {@code @JwtAuthenticationService} for authenticate
 * user details.
 * 
 * @author shaikzai
 *
 */
public interface UserCredential {

	/**
	 * Method returns username captured from request.
	 * 
	 * @return username value
	 */
	String getUsername();

	/**
	 * Method returns password captured from request.
	 * 
	 * @return password value
	 */
	String getPassword();
}
