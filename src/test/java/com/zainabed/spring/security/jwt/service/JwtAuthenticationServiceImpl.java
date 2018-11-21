package com.zainabed.spring.security.jwt.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.zainabed.spring.security.jwt.entity.UserCredential;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.entity.UserDetailImpl;
import com.zainabed.spring.security.jwt.service.JwtAuthenticationService;

@Service
@ConditionalOnProperty(prefix = "jwt", name = "authentication")
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

	@Override
	public UserDetail authenticate(UserCredential userCredential) throws AuthenticationException {
		UserDetailImpl userDetail = new UserDetailImpl();
		userDetail.setUsername(userCredential.getUsername());
		userDetail.setPassword(userCredential.getPassword());
		return userDetail;
	}

}
