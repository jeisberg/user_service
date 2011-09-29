package com.lockerz.service.user.dao;

import org.hibernate.Session;

public abstract class AuthDaoImpl implements AuthDao {
    
    protected Session session;
    
    public void setSession(Session session) {
        // set here
        this.session = session;
    }   
}