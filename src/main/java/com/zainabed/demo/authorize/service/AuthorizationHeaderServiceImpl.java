package com.zainabed.demo.authorize.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.zainabed.demo.authorize.exception.BadRequestException;

@Component
public class AuthorizationHeaderServiceImpl implements AuthorizationHeaderService {

	@Override
	public String getValue(HttpServletRequest request, String type) {
		String header = request.getHeader(AuthorizationHeaderService.AUTH_HEADER);

		if (header == null || !header.startsWith(type)) {
			throw new BadRequestException("Invalid Authorization header");
		}
		return header.replace(type, "");
	}

}
