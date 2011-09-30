package com.lockerz.service.user.auth;

import com.lockerz.service.commons.auth.Authenticator;
import com.lockerz.service.commons.auth.AuthenticatorException;

public class TokenAuthenticatorImpl implements Authenticator {

	@Override
	public long authenticate(String apiKey, String token) throws AuthenticatorException {
		// stub
		return 0;
	}
}
