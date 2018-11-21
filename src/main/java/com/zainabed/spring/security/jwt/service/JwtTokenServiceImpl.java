package com.zainabed.spring.security.jwt.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.AuthenticationToken;
import com.zainabed.spring.security.jwt.entity.UserCredential;
import com.zainabed.spring.security.jwt.entity.UserDetail;
import com.zainabed.spring.security.jwt.exception.JwtAuthenticatioException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {

	@Value("${jwt.token.secret}")
	private String tokenSecret;

	@Value("${jwt.token.expiration}")
	private String tokenExpiration;

	private Key getSecretKey() {
		return Keys.hmacShaKeyFor(tokenSecret.getBytes());
	}

	@Override
	public Claims parse(String token) throws JwtAuthenticatioException {
		Jws<Claims> claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token);
		return claims.getBody();
	}

	@Override
	public String build(UserDetail user) {
		Date now = new Date();
		Date expirationTime = new Date(now.getTime() + Long.parseLong(tokenExpiration));
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", user.getRoles())
				.setExpiration(expirationTime).signWith(getSecretKey()).compact();
	}

	public String generate() {
		return Jwts.builder().setSubject("test").signWith(getSecretKey()).compact();
	}

	@Override
	public AuthenticationToken getToken(UserDetail userDetail) {
		String token = build(userDetail);
		return new AuthenticationToken(token, AuthorizationHeaderService.AUTH_TYPE_BEARER);
	}

}
