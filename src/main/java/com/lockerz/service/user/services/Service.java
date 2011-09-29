package com.lockerz.service.user.services;

import com.lockerz.service.user.models.UserModelImpl;
import com.lockerz.service.user.models.UserLookupModelImpl;

public interface Service {
	
	public UserLookupModelImpl lookupById(long id) throws ServiceException;
	
	public UserLookupModelImpl lookupByUsername(String username) throws ServiceException;
	
	public UserModelImpl authenticate(String username, String password, String remoteIp) 
	throws ServiceException;
}
