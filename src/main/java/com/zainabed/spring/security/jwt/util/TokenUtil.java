package com.zainabed.spring.security.jwt.util;

import java.util.Base64;

/**
 * 
 * @author shaikzai
 *
 */
public class TokenUtil {

	public static String buildBasicToken(String username, String password) {
		return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}
}
