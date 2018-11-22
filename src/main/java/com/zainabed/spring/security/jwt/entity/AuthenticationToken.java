package com.zainabed.spring.security.jwt.entity;

/**
 * Class represent JWT token. it is used to transmit authentication token, token
 * type and refresh token information.
 * 
 * <p>
 * Access token is used to authorize user on each application routes which are
 * marked as secured. It contains User details as username and roles
 * (credentials) to perform role based authorization.
 * 
 * </p>
 * 
 * <p>
 * Token type for JWT authorization is Bearer and this realm is used in
 * Authorization header for each secured route.
 * </p>
 * 
 * <p>
 * Refresh token is used to reissue expired access token.
 * </p>
 * 
 * @author shaikzai
 *
 */
public class AuthenticationToken {

	private String token;
	private String type;
	private String refereshToken;

	public AuthenticationToken(String token, String type) {
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefereshToken() {
		return refereshToken;
	}

	public void setRefereshToken(String refereshToken) {
		this.refereshToken = refereshToken;
	}

}
