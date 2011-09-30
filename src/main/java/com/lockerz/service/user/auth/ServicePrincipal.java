package com.lockerz.service.user.auth;

import java.security.Principal;

public final class ServicePrincipal implements Principal {
	
    // need this
	private final long userId;
    
    public ServicePrincipal(long userId) {
    	// set here
    	this.userId = userId;
    }
    
    @Override
    public String getName() {
    	// return here and go
        return Long.toString(userId);
    }
}
