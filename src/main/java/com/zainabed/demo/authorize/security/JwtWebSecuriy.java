package com.zainabed.demo.authorize.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zainabed.demo.authorize.filter.JwtAuthorizationFilter;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)*/
public class JwtWebSecuriy extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthorizationFilter jwtAuthFilter;

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
                .antMatchers("/auth")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
