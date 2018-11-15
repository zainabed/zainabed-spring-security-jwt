package com.zainabed.demo.authorize.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.entity.AuthenticationToken;
import com.zainabed.demo.authorize.entity.UserCredential;
import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.exception.BadRequestException;

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
	public Claims parse(String token) throws BadRequestException {
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
