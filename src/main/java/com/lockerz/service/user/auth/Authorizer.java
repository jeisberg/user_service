package com.lockerz.service.user.auth;

public interface Authorizer {
	
	public void authorize(String apiKey, int serviceId) throws AuthorizerException;
}
