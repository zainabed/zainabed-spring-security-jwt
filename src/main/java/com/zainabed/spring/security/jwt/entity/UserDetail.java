package com.zainabed.spring.security.jwt.entity;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * This interface is design to fetch details of a user including his username
 * ,password and roles.
 * 
 * <p>
 * It is extended from {@link UserDetails} and has two additional methods.
 * Interface methods are used to build user object from given claims and to
 * fetch user permissions.
 * </p>
 * 
 * <p>
 * Implementation should annotate it as Spring component and set scope as
 * prototype to generate new copy of UserDetail at each request.
 * </p>
 * 
 * @author Zainul Shaikh
 *
 */
@Component
@Scope("prototype")
public interface UserDetail extends UserDetails {

	/**
	 * Method is used to generate {@link UserDetail} object. It takes information
	 * from JWT claims object and update username and roles from that claims.
	 * 
	 * @param claims JWT claims object
	 */
	void build(Claims claims);

	/**
	 * Method returns user roles assigned to him like
	 * 
	 * <ul>
	 * <li>ROLE_USER</li>
	 * <li>ROLE_ADMIN</li>
	 * </ul>
	 * 
	 * If user does not have any role then method should generate new role as
	 * ROLE_USER and assign it to role object.
	 * 
	 * @return List object user roles.
	 */
	List<String> getRoles();
}
