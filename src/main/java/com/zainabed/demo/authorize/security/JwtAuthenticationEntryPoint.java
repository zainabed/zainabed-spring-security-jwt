package com.zainabed.demo.authorize.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("------------Inside entry point with exception " + exception.getLocalizedMessage());
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getLocalizedMessage());
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 
        String message;
        if(exception.getCause() != null) {
            message = exception.getCause().getMessage();
        } else {
            message = exception.getMessage();
        }
        byte[] body = new ObjectMapper()
                .writeValueAsBytes(Collections.singletonMap("error", message));
        response.getOutputStream().write(body);
	}

}
