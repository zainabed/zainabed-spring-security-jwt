package com.zainabed.spring.security.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zainabed.spring.security.jwt.filter.JwtAuthorizationFilter;

/**
 * Enables web security for JWT authentication and authorization process. Class
 * is extend from {@link WebSecurityConfigurerAdapter} and overrides configure
 * method
 * 
 * It adds authorization mechanism for all HTTP request of application and
 * permits only authentication request.
 * 
 * 
 * @author Zainul Shaikh
 *
 */
public class JwtWebSecuriy extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthorizationFilter jwtAuthFilter;

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;

	/**
	 * Basic configuration from authentication and authorization. it also configure
	 * JWT filter and exception handler.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/auth").permitAll().antMatchers("/**")
				.authenticated().and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
