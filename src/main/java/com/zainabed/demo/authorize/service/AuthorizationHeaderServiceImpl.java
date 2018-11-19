package com.zainabed.demo.authorize.service;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.UserCredentailImpl;

@Component
public class AuthorizationHeaderServiceImpl implements AuthorizationHeaderService {

	@Override
	public String getValue(HttpServletRequest request, String type) {
		String header = request.getHeader(AuthorizationHeaderService.AUTH_HEADER);

		if (header != null && header.startsWith(type)) {
			return header.replace(type, "");
		}
		return null;
	}

	@Override
	public UserCredentailImpl getBasicUserCredentials(HttpServletRequest request) {
		String token = getValue(request, AUTH_TYPE_BASIC);
		byte[] credentailInByte = Base64.getDecoder().decode(token);
		String credentailValue = new String(credentailInByte);
		String[] credentailArray = credentailValue.split(":");
		UserCredentailImpl userCredential = new UserCredentailImpl();
		userCredential.setUsername(credentailArray[0]);
		userCredential.setPassword(credentailArray[1]);
		return userCredential;
	}

}
