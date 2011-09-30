package com.lockerz.service.user.utilities;

import java.util.HashMap;
import org.springframework.http.HttpStatus;

import com.lockerz.service.user.auth.AuthenticatorException;
import com.lockerz.service.user.services.ServiceException;

public class ExceptionHelper {
	
	public static final int EXCEPTION = 1;

	public static ServiceException fatal(double code, String message, HttpStatus httpStatus) {
		// create the error
		HashMap<Double, String> messages = new HashMap<Double, String>(1);
		// put the error
		messages.put(new Double(code), message);
		// throw the error here
		return new ServiceException(message, messages, httpStatus);
	}
	
	public static AuthenticatorException authenticationFatal(double code, String message, HttpStatus httpStatus)
	{
	    HashMap<Double, String> messages = new HashMap<Double, String>(1);
        // put the error
        messages.put(new Double(code), message);
        // throw the error here
        return new AuthenticatorException(message, messages, httpStatus);
	    
	}
}
