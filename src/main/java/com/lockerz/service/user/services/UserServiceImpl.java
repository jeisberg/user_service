package com.lockerz.service.user.services;

import java.util.HashMap;
import org.hibernate.Session;
import com.lockerz.service.user.dao.DaoImpl;
import com.lockerz.service.user.dao.DaoFactory;
import com.lockerz.service.user.dao.DaoException;
import com.lockerz.service.user.utilities.Utilities;
import com.lockerz.service.user.models.UserModelImpl;
import com.lockerz.service.user.utilities.ExceptionHelper;
import com.lockerz.service.user.models.UserLookupModelImpl;

import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserServiceImpl extends ServiceImpl {
	
	// need this
	public static final int ACTIVE = 1;
	
	// need these
	public static final double LOOKUP_FAILED = 100.01;
	public static final double LOOKUP_EMPTY = 100.51;
	
	// need these
	public static final double EMPTY_USERNAME = 200.11;
	public static final double EMPTY_PASSWORD = 200.21;
	public static final double EMPTY_USER = 200.31;
	public static final double DISABLED_USER = 200.41;
	public static final double INVALID_PASSWORD = 200.51;
	
	public UserServiceImpl(HibernateTemplate[] pods) {
		// set the pods here
		this.pods = pods;
	}
	
	@Override
	public UserLookupModelImpl lookupByUsername(String username) throws ServiceException {
		// get the session here
		Session session = pods[Nodes.LOCATOR.ordinal()].getSessionFactory().getCurrentSession();
		// create the factory here
		DaoFactory factory = DaoFactory.instance(DaoFactory.USER);
		// create the instance here
		DaoImpl dao = factory.getUserDao(session);
		// need this
		UserLookupModelImpl row = null;
		// try
		try {
			// call here
			row = dao.lookupByUsername(username);
			// sanity check
			if(row == null) {
				// create the message
				String message = "lookup by username [" + username + "] is empty";
				// create the exception
				throw ExceptionHelper.fatal(LOOKUP_EMPTY, message, HttpStatus.UNAUTHORIZED);
			}
		// catch here
		} catch(DaoException e) {
			// create the message
			String message = "lookup by username [" + username + "] failed";
			// create the exception
			throw ExceptionHelper.fatal(LOOKUP_FAILED, message, HttpStatus.INTERNAL_SERVER_ERROR);
		// clean up here
		} finally {
			// close here
			dao.close();
		}
		// return here
		return row;
	}

	@Override
	public UserLookupModelImpl lookupById(long id) throws ServiceException {
		// get the session here
		Session session = pods[Nodes.LOCATOR.ordinal()].getSessionFactory().getCurrentSession();
		// create the factory here
		DaoFactory factory = DaoFactory.instance(DaoFactory.USER);
		// create the instance here
		DaoImpl dao = factory.getUserDao(session);
		// need this
		UserLookupModelImpl row = null;
		// try
		try {
			// call here
			row = dao.lookupById(id);
			// sanity check
			if(row == null) {
				// create the message
				String message = "lookup by id [" + id + "] is empty";
				// create the exception
				throw ExceptionHelper.fatal(LOOKUP_EMPTY, message, HttpStatus.UNAUTHORIZED);
			}	
		// catch here
		} catch(DaoException e) {
			// create the message
			String message = "lookup by id [" + id + "] failed";
			// create the exception
			throw ExceptionHelper.fatal(LOOKUP_FAILED, message, HttpStatus.INTERNAL_SERVER_ERROR);
		// clean up here
		} finally {
			// close here
			dao.close();
		}
		// return here
		return row;
	}
	
	@Override
	public UserModelImpl authenticate(String username, String password, String remoteIp) 
	throws ServiceException {	
		// need this
		HashMap<Double,String> validation = new HashMap<Double, String>();
		// validate the data here
		if(Utilities.isNullOrEmpty(username)) {
			// put the error
			validation.put(EMPTY_USERNAME, "username cannot be empty");
		}
		// validate the data here
		if(Utilities.isNullOrEmpty(password)) {
			// put the error
			validation.put(EMPTY_PASSWORD, "password cannot be empty");
		}
		// sanity check
		if(!validation.isEmpty()) {
			// validation errors
			throw new ServiceException(null, validation, HttpStatus.BAD_REQUEST);
		// passed
		} else {
			// try
			try {
				// need this
				UserLookupModelImpl lookup = lookupByUsername(username);
				// get the session here
				Session session = pods[lookup.getPodId()].getSessionFactory().getCurrentSession();
				// create the factory here
				DaoFactory factory = DaoFactory.instance(DaoFactory.USER);
				// create the instance here
				DaoImpl dao = factory.getUserDao(session);
				// get the user here
				UserModelImpl user = dao.getUser(lookup.getId());
				// sanity check
				if(user == null) {
					// create the message
					String message = "unable to locate user [" + username + "] in pod " + lookup.getPodId();
					// create the exception
					throw ExceptionHelper.fatal(EMPTY_USER, message, HttpStatus.INTERNAL_SERVER_ERROR);
				// good user
				} else {
					// sanity check
					if(user.getStatus() == ACTIVE) {
						// get the seed here
						byte[] seed = Utilities.extractSeed(user.getPassword());
						// get the hash here
						String hash = Utilities.hashPassword(password, seed);
						// need this
						String extractedHash = Utilities.extractPasswordHash(user.getPassword());
						// sanity check
						if(hash != null && hash.equals(extractedHash)) {
							// check md5 instance
							hash = Utilities.asHex(Utilities.md5(password));
							// secondary validation
							if (hash.equals(user.getPassword())) {
								// create the new password
								String newPassword = Utilities.hashAndSeedPassword(password);
								// set the user here
								user.setPassword(newPassword);
								// set the time stamp
								user.setModified(new java.sql.Timestamp(System.currentTimeMillis()));
								// update the user
								dao.updateUser(user);
							}
							// return here
							return user;
						} else {
							// create the message
							String message = "invalid password for user [" + username + "]";
							// create the exception
							throw ExceptionHelper.fatal(INVALID_PASSWORD, message, HttpStatus.UNAUTHORIZED);
						}
					} else {
						// create the message
						String message = "user [" + username + "] has status " + user.getStatus();
						// create the exception
						throw ExceptionHelper.fatal(DISABLED_USER, message, HttpStatus.UNAUTHORIZED);
					}
				}
				// catch and throw here
			} catch(DaoException e) {
				// create the message
				String message = this.getClass().getName() + " -> " + e.getMessage();
				// create the exception
				throw ExceptionHelper.fatal(ExceptionHelper.EXCEPTION, message, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}