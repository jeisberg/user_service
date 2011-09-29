package com.lockerz.service.user.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lockerz.service.user.dao.AuthDao;
import com.lockerz.service.user.dao.AuthDaoImpl;
import com.lockerz.service.user.dao.DaoException;
import com.lockerz.service.user.dao.InvalidTokenException;
import com.lockerz.service.user.services.ServiceException;
import com.lockerz.service.user.utilities.ExceptionHelper;

public class TokenAuthenticatorImpl extends AuthenticatorImpl {
	
	// create the logger here
	@SuppressWarnings({ "unused" })
	private static Logger LOG = LoggerFactory.getLogger(KeyAuthorizerImpl.class);
	AuthDaoImpl dao;
	
	
	// need this
	public static final double UNAUTHORIZED = 100.01;
	
	public TokenAuthenticatorImpl(HibernateTemplate authTemplate, AuthDaoImpl daoImpl)
	{
	    daoImpl.setSession(authTemplate.getSessionFactory().getCurrentSession());
	    this.dao = daoImpl;
	}
	
	@Override
	public long authenticate(String apiKey, String token) throws AuthenticatorException, ServiceException {

	    try
		{
	        dao.startSession();
		    return dao.authenticateToken(apiKey, token);
		} catch (InvalidTokenException ite)
		{
		    throw new AuthenticatorException(ite.getMessage(), null, HttpStatus.FORBIDDEN);
		    
		} catch (DaoException de)
		{
		    throw ExceptionHelper.fatal(ExceptionHelper.EXCEPTION, de.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally
		{
		    dao.closeSession();
		}
		
	}
}
