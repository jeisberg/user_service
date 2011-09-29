package com.lockerz.service.user.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import com.lockerz.service.user.models.TokenAuthModel;
import com.lockerz.service.user.models.UserLookupModelImpl;

public class TokenAuthDaoImpl extends AuthDaoImpl
{
    
    public TokenAuthDaoImpl()
    {
        
    }

    @Override
    public long authenticateToken(String apiKey, String token) throws InvalidTokenException, DaoException
    {
    
        try
        {
            List rows = session
                        .createCriteria(UserLookupModelImpl.class)
                        .add( Restrictions.eq( "token", token ) )
                        .add( Restrictions.eq( "token", token ) )
                        .list();
            if(rows == null || rows.size() != 1)
              throw new InvalidTokenException("Cannot find token - [" + token +"]. User is probably not authenticated");
            
            return ((TokenAuthModel) rows.get(0)).getUid();
        } catch (Exception e)
        {
            String message = this.getClass().getName() + " -> " + e.getMessage();
            // throw an exception here
            throw new DaoException(message);        
        }
    }

}
