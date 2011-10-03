package com.lockerz.service.user.utilities;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import com.lockerz.service.commons.services.ServiceException;

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
}
