package com.zainabed.demo.authorize.service;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zainabed.demo.authorize.entity.UserDetail;
import com.zainabed.demo.authorize.exception.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {

	@Value("${jwt.secret.key}")
	private String secretKeyValue = "dksjflkdsjfkldjsklfjdslkjflkdsjfkldsjk";
	private Key secretKey;

	public JwtTokenServiceImpl() {
		setSecretKey();
	}

	private void setSecretKey() {
		secretKey = Keys.hmacShaKeyFor(secretKeyValue.getBytes());
	}

	@Override
	public Claims parse(String token) throws BadRequestException {
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		return claims.getBody();
	}

	@Override
	public String build(UserDetail user) {
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", user.getRoles()).signWith(secretKey).compact();
	}

	public String generate() {
		return Jwts.builder().setSubject("test").signWith(secretKey).compact();
	}

}
