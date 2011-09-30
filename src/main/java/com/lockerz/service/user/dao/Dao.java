package com.lockerz.service.user.dao;

import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.commons.dao.DaoException;
import com.lockerz.service.user.models.UserLookupModel;

public interface Dao {
	
	public void close();
	
	public UserLookupModel lookupById(long id) throws DaoException;
	
	public UserLookupModel lookupByUsername(String username) throws DaoException;
	
	public UserModel getUser(long id) throws DaoException;
	
	public void updateUser(UserModel user) throws DaoException;
}
