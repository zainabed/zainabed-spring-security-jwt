package com.zainabed.demo.authorize.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public interface UserDetailService {

	UsernamePasswordAuthenticationToken buildAuthentication(Claims claim);

}
