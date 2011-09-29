package com.lockerz.service.user.controllers;

public class ServiceControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceControllerException() {
		// call parent
		super();
	}
	
	public ServiceControllerException(String message) {
		// call parent
		super(message);
	}
}