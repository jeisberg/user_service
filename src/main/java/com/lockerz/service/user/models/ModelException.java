package com.lockerz.service.user.models;

public class ModelException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModelException() {
		// call parent
		super();
	}
	
	public ModelException(String message) {
		// call parent
		super(message);
	}
}
