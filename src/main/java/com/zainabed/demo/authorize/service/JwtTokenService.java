package com.zainabed.demo.authorize.service;

import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.AuthenticationToken;
import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.exception.BadRequestException;

import io.jsonwebtoken.Claims;

@Component
public interface JwtTokenService {

	Claims parse(String token) throws BadRequestException;
	String build(UserDetail userDetail);
	AuthenticationToken getToken(UserDetail userDetail);
	String generate();
	
}
