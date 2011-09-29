package com.lockerz.service.user.client;

import com.lockerz.service.user.models.UserModelImpl;

public interface Client {
	
	public UserModelImpl authenticate(String token, String username, String password, String remoteIp) 
	throws ClientException;
}
