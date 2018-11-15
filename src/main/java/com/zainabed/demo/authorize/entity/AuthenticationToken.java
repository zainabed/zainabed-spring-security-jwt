package com.zainabed.demo.authorize.entity;

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
