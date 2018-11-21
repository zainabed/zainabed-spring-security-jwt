package com.zainabed.spring.security.jwt.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
@Scope("prototype")
public class UserDetailImpl implements UserDetail {
	private boolean credentailExpired = false;
	private boolean enabled = true;
	private boolean expired = false;
	private boolean locked = false;
	private String password;
	private List<String> roles;
	private String username;

	@SuppressWarnings("unchecked")
	@Override
	public void build(Claims claims) {
		username = claims.getSubject();
		roles = (List<String>) claims.get("roles");
		if (roles == null) {
			roles = new ArrayList<>();
			roles.add("ROLE_USER");
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public List<String> getRoles() {

		return roles;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return expired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return locked;
	}

	public boolean isCredentailExpired() {
		return credentailExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return credentailExpired;
	}

	@Override
	public boolean isEnabled() {

		return false;
	}

	public boolean isExpired() {
		return expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setCredentailExpired(boolean credentailExpired) {
		this.credentailExpired = credentailExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
