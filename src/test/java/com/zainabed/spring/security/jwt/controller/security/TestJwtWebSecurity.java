package com.zainabed.spring.security.jwt.controller.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.zainabed.spring.security.jwt.annotation.EnableJwtSecurity;
import com.zainabed.spring.security.jwt.security.JwtWebSecuriy;

@EnableJwtSecurity
public class TestJwtWebSecurity extends JwtWebSecuriy{

}
