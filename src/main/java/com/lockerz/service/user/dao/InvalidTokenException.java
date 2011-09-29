package com.lockerz.service.user.dao;

public class InvalidTokenException extends Exception
{
    private static final long serialVersionUID = 1L;

    public InvalidTokenException() {
        // call parent
        super();
    }
    
    public InvalidTokenException(String message) {
        // call parent
        super(message);
    }

}
