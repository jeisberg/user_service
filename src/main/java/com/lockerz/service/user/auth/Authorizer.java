package com.lockerz.service.user.auth;

public interface Authorizer {
	
	public void authorize(String token, int serviceId) throws AuthorizerException;
}
