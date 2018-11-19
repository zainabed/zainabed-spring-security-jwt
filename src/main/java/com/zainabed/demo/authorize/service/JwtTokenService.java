package com.zainabed.demo.authorize.service;

import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.AuthenticationToken;
import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.exception.BadRequestException;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public interface JwtTokenService {

	/**
	 * 
	 * @param token
	 * @return
	 * @throws BadRequestException
	 */
	Claims parse(String token) throws BadRequestException;

	/**
	 * 
	 * @param userDetail
	 * @return
	 */
	String build(UserDetail userDetail);

	/**
	 * 
	 * @param userDetail
	 * @return
	 */
	AuthenticationToken getToken(UserDetail userDetail);

	/**
	 * 
	 * @return
	 */
	String generate();

}
