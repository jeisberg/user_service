package com.lockerz.service.user.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lockerz.service.user.dao.AuthDao;
import com.lockerz.service.user.dao.AuthDaoImpl;

public class TokenAuthenticatorImpl extends AuthenticatorImpl {
	
	// create the logger here
	@SuppressWarnings({ "unused" })
	private static Logger LOG = LoggerFactory.getLogger(KeyAuthorizerImpl.class);
	AuthDao dao;
	
	
	// need this
	public static final double UNAUTHORIZED = 100.01;
	
	public TokenAuthenticatorImpl(HibernateTemplate authTemplate, AuthDaoImpl daoImpl)
	{
	    daoImpl.setSession(authTemplate.getSessionFactory().getCurrentSession());
	    dao = daoImpl;
	}
	
	@Override
	public long authenticate(String token) throws AuthenticatorException {
		// return slug here
		return -1;
	}
}
