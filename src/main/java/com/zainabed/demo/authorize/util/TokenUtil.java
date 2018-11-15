package com.zainabed.demo.authorize.util;

import java.util.Base64;

public class TokenUtil {

	public static String buildBasicToken(String username, String password) {
		return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}
}
