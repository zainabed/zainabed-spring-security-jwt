package com.zainabed.spring.security.jwt.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * @author shaikzai
 *
 */
@Component
public interface UserDetailService {

	/**
	 * 
	 * @param claim
	 * @return
	 */
	UsernamePasswordAuthenticationToken buildAuthentication(Claims claim);

}
