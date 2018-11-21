package com.zainabed.spring.security.jwt.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.zainabed.spring.security.jwt.controller.JwtAuthenticationController;

//@Configuration
public class AuthControllerMappingConfig {

	@Bean
	//@ConditionalOnProperty(prefix = "jwt", name = "authentication")
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping handler = new SimpleUrlHandlerMapping();
		Map<String, Object> mapping = new HashMap<>();
		mapping.put("/auth", controller());
		handler.setUrlMap(mapping);
		return handler;
	}

	
	//@ConditionalOnProperty(prefix = "jwt", name = "authentication")
	@Bean
	public JwtAuthenticationController controller() {
		return new JwtAuthenticationController();
	}
}
