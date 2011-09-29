package com.lockerz.service.user.dao;

import org.hibernate.Session;


public class UserDaoFactory extends DaoFactory {
 
	public UserDaoImpl getUserDao(Session session) {
    	// return here
        return (UserDaoImpl) instantiateDao(UserDaoImpl.class, session);
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private DaoImpl instantiateDao(Class daoClass, Session session) {
        // try
    	try {
    		// get the instance here with the session as an argument to the constructor
    		Object instance = daoClass.getDeclaredConstructor(Session.class).newInstance(session);
    		// cast to the implementation here
    		DaoImpl dao = (DaoImpl) instance;
    		// set the current session here
            dao.setSession(session);
            // return here
            return dao;
        // catch and throw here
        } catch (Exception e) {
        	// throw a new exception here
            throw new RuntimeException("cannot create instance of: " + daoClass, e);
        }
    }
}
