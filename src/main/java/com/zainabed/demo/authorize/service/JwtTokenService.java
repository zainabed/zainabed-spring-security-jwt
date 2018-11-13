package com.zainabed.demo.authorize.service;

import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.exception.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public interface JwtTokenService {

	Claims parse(String token) throws BadRequestException;
	String build(UserDetail user);
	String generate();
	
}
