package com.zainabed.spring.security.jwt.entity;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * @author shaikzai
 *
 */
@Component
@Scope("prototype")
public interface UserDetail extends UserDetails {

	void build(Claims climas);

	String getUsername();

	String getPassword();

	List<String> getRoles();
}
