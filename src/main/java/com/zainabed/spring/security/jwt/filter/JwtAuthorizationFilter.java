package com.zainabed.spring.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zainabed.spring.security.jwt.security.JwtAuthenticationEntryPoint;
import com.zainabed.spring.security.jwt.service.AuthorizationHeaderService;
import com.zainabed.spring.security.jwt.service.JwtTokenService;
import com.zainabed.spring.security.jwt.service.UserDetailService;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author Zainul Shaikh
 *
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	AuthorizationHeaderService authHeaderService;

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	JwtTokenService jwtTokenService;

	@Autowired
	JwtAuthenticationEntryPoint entryPoint;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = authHeaderService.getValue(request, AuthorizationHeaderService.AUTH_TYPE_BEARER);

			if (token == null) {
				filterChain.doFilter(request, response);
				return;
			}

			Claims claims = jwtTokenService.parse(token);
			UsernamePasswordAuthenticationToken authentication = userDetailService.buildAuthentication(claims);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch (AuthenticationException exception) {
			entryPoint.commence(request, response, exception);
			return;
		}

		filterChain.doFilter(request, response);

	}

}
