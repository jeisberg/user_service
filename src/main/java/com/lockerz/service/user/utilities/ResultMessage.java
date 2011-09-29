package com.lockerz.service.user.utilities;

import java.util.Map;
import java.util.HashMap;

public class ResultMessage {
	
	// need this
	private static final long serialVersionUID = 1L;

	// need this
	String message = null;
	
	// need this
	HashMap<Double, String> messages = new HashMap<Double, String>();

	public ResultMessage() {
		// call parent
		super();
	}
	
	public ResultMessage(String message, HashMap<Double, String> messages) {
		// set the message
		this.message = message;
		// set the messages
		this.messages = messages;
	}
	
	@SuppressWarnings("unchecked")
	public ResultMessage(Map<String, Object> map) {
		// sanity check
		if(map.get("message") != null) {
			// set here
			this.message = (String) map.get("message");
		}
		// sanity check
		if(map.get("messages") != null) {
			// set here
			this.messages = (HashMap<Double, String>) map.get("messages");
		}
	}

	public String getMessage() {
		// return here
		return message;
	}

	public void setMessage(String message) {
		// set here
		this.message = message;
	}

	public HashMap<Double, String> getMessages() {
		// return here
		return messages;
	}

	public void setMessages(HashMap<Double, String> messages) {
		// set here
		this.messages = messages;
	}
	
	@Override
	public String toString() {
		return "ResultMessage [message=" + message + ", messages=" + messages + "]";
	}
}
