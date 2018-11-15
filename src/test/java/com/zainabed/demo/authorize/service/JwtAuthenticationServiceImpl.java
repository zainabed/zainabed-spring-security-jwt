package com.zainabed.demo.authorize.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.zainabed.demo.authorize.entity.UserCredential;
import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.entity.UserDetailImpl;

@Service
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

	@Override
	public UserDetail authenticate(UserCredential userCredential) throws AuthenticationException {
		UserDetailImpl userDetail = new UserDetailImpl();
		userDetail.setUsername(userCredential.getUsername());
		userDetail.setPassword(userCredential.getPassword());
		return userDetail;
	}

}
