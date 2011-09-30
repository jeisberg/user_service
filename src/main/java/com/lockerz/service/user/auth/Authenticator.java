package com.lockerz.service.user.auth;

public interface Authenticator {
	
	public long authenticate(String apiKey, String token) throws AuthenticatorException;
}