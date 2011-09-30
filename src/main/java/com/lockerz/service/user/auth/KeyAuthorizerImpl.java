package com.lockerz.service.user.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lockerz.service.commons.auth.Authorizer;
import com.lockerz.service.commons.auth.AuthorizerException;

public class KeyAuthorizerImpl implements Authorizer {
	
	// create the logger here
	@SuppressWarnings({ "unused" })
	private static Logger LOG = LoggerFactory.getLogger(KeyAuthorizerImpl.class);

	// need this
	private String apiKey = null;
	
	// need this
	public static final double UNAUTHORIZED = 100.01;
	
	// create the setter
	public void setApiKey(String apiKey) {
		// set the token here
		this.apiKey = apiKey;
	}
	
	public void authorize(String apiKey, int serviceId) throws AuthorizerException {
		// return here
		if(apiKey == null || !apiKey.equals(this.apiKey)) {
			// create the message
			String message = "ApiKey [" + apiKey + "] does not have access to service [" + serviceId + "]";
			// create the exception
			throw new AuthorizerException(message);
		}
	}
}
