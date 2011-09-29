package com.lockerz.service.user.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyAuthorizerImpl extends AuthorizerImpl {
	
	// create the logger here
	@SuppressWarnings({ "unused" })
	private static Logger LOG = LoggerFactory.getLogger(KeyAuthorizerImpl.class);

	// need this
	private String token = null;
	
	// need this
	public static final double UNAUTHORIZED = 100.01;
	
	// create the setter
	public void setToken(String token) {
		// set the token here
		this.token = token;
	}
	
	public void authorize(String token, int serviceId) throws AuthorizerException {
		// return here
		if(token == null || !token.equals(this.token)) {
			// create the message
			String message = "Token [" + token + "] does not have access to service [" + serviceId + "]";
			// create the exception
			throw new AuthorizerException(message);
		}
	}
}
