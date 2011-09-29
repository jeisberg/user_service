package com.lockerz.service.user.auth;

public interface Authenticator {
	
	public long authenticate(String token) throws AuthenticatorException;
}
