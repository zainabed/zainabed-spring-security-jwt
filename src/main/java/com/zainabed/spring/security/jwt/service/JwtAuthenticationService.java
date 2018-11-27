package com.zainabed.spring.security.jwt.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.zainabed.spring.security.jwt.entity.UserCredential;
import com.zainabed.spring.security.jwt.entity.UserDetail;

/**
 * Interface is design to perform authentication process. user detail is
 * captured from HTTP Basic authentication process
 * 
 * <pre>
 *  Authorization: Basic {username:password}
 * </pre>
 * 
 * Once system receive authentication request it generates
 * {@link UserCredential} object and trigger authentication method of
 * {@link JwtAuthenticationService} implementation.
 * 
 * Implementation should use this information to validate user and if
 * information is valid then generate {@link UserDetail} object. otherwise throw
 * exception {@link AuthenticationException}
 * 
 * @author Zainul Shaikh
 *
 */
@Component
public interface JwtAuthenticationService {

	UserDetail authenticate(UserCredential userCredential) throws AuthenticationException;
}
