package com.zainabed.spring.security.jwt.service;

import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.AuthenticationToken;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.exception.JwtAuthenticatioException;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public interface JwtTokenService {

	Claims parse(String token) throws JwtAuthenticatioException;

	String build(UserDetail userDetail);

	AuthenticationToken getToken(UserDetail userDetail);

	String generate();

}
