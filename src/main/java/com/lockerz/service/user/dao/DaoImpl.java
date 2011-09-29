package com.lockerz.service.user.dao;

import org.hibernate.Session;

import com.lockerz.service.user.dao.Dao;

public abstract class DaoImpl implements Dao {
    
    protected Session session;
	
	public void setSession(Session session) {
		// set here
		this.session = session;
	}	
}
