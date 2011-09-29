package com.lockerz.service.user.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenAuthenticatorImpl extends AuthenticatorImpl {
	
	// create the logger here
	@SuppressWarnings({ "unused" })
	private static Logger LOG = LoggerFactory.getLogger(TokenAuthorizerImpl.class);
	
	// need this
	public static final double UNAUTHORIZED = 100.01;
	
	@Override
	public long authenticate(String token) throws AuthenticatorException {
		// return slug here
		return -1;
	}
}
