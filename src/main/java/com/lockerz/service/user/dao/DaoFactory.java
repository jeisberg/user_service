package com.lockerz.service.user.dao;

import org.hibernate.Session;

public abstract class DaoFactory {
	
	// need this here
	@SuppressWarnings("rawtypes")
	public static final Class USER = com.lockerz.service.user.dao.UserDaoFactory.class;
	 
	@SuppressWarnings("rawtypes")
	public static DaoFactory instance(Class factory) {
		// try
		try {
			// return here
            return (DaoFactory) factory.newInstance();
        // catch here
        } catch (Exception e) {
        	// throw a new exception here
            throw new RuntimeException("cannot create instance of: " + factory);
        }
    }
 
    // Add DAO interfaces here
    public abstract UserDaoImpl getUserDao(Session session);
}
