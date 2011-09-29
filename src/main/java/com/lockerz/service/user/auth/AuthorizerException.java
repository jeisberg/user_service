package com.lockerz.service.user.auth;

import java.util.HashMap;
import org.springframework.http.HttpStatus;

public class AuthorizerException extends Exception {

	// need this
	private static final long serialVersionUID = 1L;
	
	// need this
	HashMap<Double, String> messages = new HashMap<Double, String>();
	
	// need this
	HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public AuthorizerException() {
		// call parent
		super();
	}
	
	public AuthorizerException(String message) {
		// call parent
		super(message);
	}
	
	public AuthorizerException(String message, HashMap<Double, String> messages, HttpStatus httpStatus) {
		// call parent
		super(message);
		// set the messages
		this.messages = messages;
		// set the status code
		this.httpStatus = httpStatus;
	}

	public HashMap<Double, String> getMessages() {
		// return here
		return messages;
	}

	public void setMessages(HashMap<Double, String> messages) {
		// set here
		this.messages = messages;
	}

	public HttpStatus getHttpStatus() {
		// return here
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		// set here
		this.httpStatus = httpStatus;
	}
}