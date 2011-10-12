package com.lockerz.service.user.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.user.models.UserProfile;
import com.lockerz.service.commons.dao.DaoException;
import com.lockerz.service.user.models.UserLookupModel;

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
	public UserLookupModel lookupByUsername(String username) throws DaoException {
		// try
		try {
			// need this
			UserLookupModel row = null;
			// need this
			@SuppressWarnings("rawtypes")
			List rows = session
				.createCriteria(UserLookupModel.class)
				.add( Restrictions.eq( "email", username ) )
				.list();
			// sanity check
			if(rows != null && rows.size() == 1) {
				// need this
				row = (UserLookupModel) rows.get(0);
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
	public UserLookupModel lookupById(long id) throws DaoException {
		// try
		try {
			// need this
			UserLookupModel row = null;
			// need this
			@SuppressWarnings("rawtypes")
			List rows = session
				.createCriteria(UserLookupModel.class)
				.add( Restrictions.eq( "id", id ) )
				.list();
			// sanity check
			if(rows != null && rows.size() == 1) {
				// need this
				row = (UserLookupModel) rows.get(0);
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
	public UserModel getUser(long id) throws DaoException {
		// try
		try {
			// need this
			UserModel user = (UserModel) session.get(UserModel.class, id);
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
	public void updateUser(UserModel user) throws DaoException {
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
	public UserProfile profile(long userId) throws DaoException {
		// return here
		UserProfile userProfile = new UserProfile();
		// set the id
		userProfile.setId(userId);
		// set the token
		userProfile.setToken("token");
		// return here
		return userProfile;
	}
	
	@Override
	public void close() {
		// commit the transaction
		session.getTransaction().commit();
	}
}
