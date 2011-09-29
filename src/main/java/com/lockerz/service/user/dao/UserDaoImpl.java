package com.lockerz.service.user.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.lockerz.service.user.models.UserModelImpl;
import com.lockerz.service.user.models.UserLookupModelImpl;

public class UserDaoImpl extends DaoImpl {
	
	protected UserDaoImpl() {
		// super here
		super();
	}
	
	public UserDaoImpl(Session session) {
		// super here
		super();
		// set the session here
		setSession(session);
		// start the session
		session.beginTransaction();
	}
	
	@Override
	public UserLookupModelImpl lookupByUsername(String username) throws DaoException {
		// try
		try {
			// need this
			UserLookupModelImpl row = null;
			// need this
			@SuppressWarnings("rawtypes")
			List rows = session
				.createCriteria(UserLookupModelImpl.class)
				.add( Restrictions.eq( "email", username ) )
				.list();
			// sanity check
			if(rows != null && rows.size() == 1) {
				// need this
				row = (UserLookupModelImpl) rows.get(0);
			}
			// return here
			return row;
		// catch here
		} catch(Exception e) {
			// create message
			String message = this.getClass().getName() + " -> " + e.getMessage();
			// throw an exception here
			throw new DaoException(message);
		}
	}
	
	@Override
	public UserLookupModelImpl lookupById(long id) throws DaoException {
		// try
		try {
			// need this
			UserLookupModelImpl row = null;
			// need this
			@SuppressWarnings("rawtypes")
			List rows = session
				.createCriteria(UserLookupModelImpl.class)
				.add( Restrictions.eq( "id", id ) )
				.list();
			// sanity check
			if(rows != null && rows.size() == 1) {
				// need this
				row = (UserLookupModelImpl) rows.get(0);
			}
			// return here
			return row;
		// catch here
		} catch(Exception e) {
			// create message
			String message = this.getClass().getName() + " -> " + e.getMessage();
			// throw an exception here
			throw new DaoException(message);
		}
	}
	
	@Override
	public UserModelImpl getUser(long id) throws DaoException {
		// try
		try {
			// need this
			UserModelImpl user = (UserModelImpl) session.get(UserModelImpl.class, id);
			// need this
			return user;
		// catch here
		} catch(Exception e) {
			// create message
			String message = this.getClass().getName() + " -> " + e.getMessage();
			// throw a client exception
			throw new DaoException(message);
		}
	}
	
	@Override
	public void updateUser(UserModelImpl user) throws DaoException {
		// try
		try {
			// need this
			session.update(user);
		// catch here
		} catch(Exception e) {
			// create message
			String message = this.getClass().getName() + " -> " + e.getMessage();
			// throw a client exception
			throw new DaoException(message);
		}
	}
	
	@Override
	public void close() {
		// commit the transaction
		session.getTransaction().commit();
	}
}
