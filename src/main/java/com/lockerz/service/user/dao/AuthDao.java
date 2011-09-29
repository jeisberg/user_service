package com.lockerz.service.user.dao;

public interface AuthDao
{
    public long authenticateToken(String apiKey, String token) throws InvalidTokenException, DaoException;

}
