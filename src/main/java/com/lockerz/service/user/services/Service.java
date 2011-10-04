package com.lockerz.service.user.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestHeader;

import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.user.models.UserLookupModel;
import com.lockerz.service.commons.services.ServiceException;

public interface Service {
	
	public UserLookupModel lookupById(long id) throws ServiceException;
	
	public UserLookupModel lookupByUsername(String username) throws ServiceException;
	
	public String login(String apiKey, String username, String password, String remoteIp) 
	throws ServiceException;
}
