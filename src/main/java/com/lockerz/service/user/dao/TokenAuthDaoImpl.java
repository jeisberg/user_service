package com.lockerz.service.user.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import com.lockerz.service.user.models.TokenAuthModel;
import com.lockerz.service.user.models.UserLookupModelImpl;

public class TokenAuthDaoImpl extends AuthDaoImpl
{

    @Override
    public long authenticateToken(String apiKey, String token) throws InvalidTokenException, DaoException
    {
        try
        {
            List rows = session
                        .createCriteria(UserLookupModelImpl.class)
                        .add( Restrictions.eq( "api_key", apiKey ) )
                        .add( Restrictions.eq( "token", token ) )
                        .list();
            if(rows == null || rows.size() != 1)
              throw new InvalidTokenException("Cannot find token - [" + token +"]. User is probably not authenticated");
            
            TokenAuthModel tokenModel = (TokenAuthModel) rows.get(0);
            if(tokenModel.getToken() == null)
                throw new InvalidTokenException("Cannot find token - [" + token +"]. User is probably not authenticated");

            return tokenModel.getUid();
            
        } catch (HibernateException e)
        {
            String message = this.getClass().getName() + " -> " + e.getMessage();
            // throw an exception here
            throw new DaoException(message);        
        }
    }
}
