package com.lockerz.service.user.dao;

import com.lockerz.service.user.models.UserModelImpl;
import com.lockerz.service.user.models.UserLookupModelImpl;

public interface Dao {
	
	public void close();
	
	public UserLookupModelImpl lookupById(long id) throws DaoException;
	
	public UserLookupModelImpl lookupByUsername(String username) throws DaoException;
	
	public UserModelImpl getUser(long id) throws DaoException;
	
	public void updateUser(UserModelImpl user) throws DaoException;
}
