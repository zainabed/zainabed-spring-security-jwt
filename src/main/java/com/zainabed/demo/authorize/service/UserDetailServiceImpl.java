package com.zainabed.demo.authorize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserDetail userDetail;

	@Override
	public UsernamePasswordAuthenticationToken buildAuthentication(Claims claims) {
		userDetail.build(claims);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		return authentication;
	}

}
