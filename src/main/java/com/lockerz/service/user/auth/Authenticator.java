package com.lockerz.service.user.auth;

import com.lockerz.service.user.services.ServiceException;

public interface Authenticator {
	
	public long authenticate(String apiKey, String token) throws AuthenticatorException;
}
