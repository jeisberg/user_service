package com.lockerz.service.user.services;

import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.user.models.UserLookupModel;
import com.lockerz.service.commons.services.ServiceException;

public interface Service {
	
	public UserLookupModel lookupById(long id) throws ServiceException;
	
	public UserLookupModel lookupByUsername(String username) throws ServiceException;
	
	public UserModel login(String username, String password, String remoteIp) 
	throws ServiceException;
}
