package com.lockerz.service.user.client;

import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.commons.client.ClientException;

public interface Client {
	
	public String login(String token, String username, String password, String remoteIp) 
	throws ClientException;
}
