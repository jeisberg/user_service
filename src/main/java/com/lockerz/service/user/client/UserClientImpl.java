package com.lockerz.service.user.client;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.LinkedHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ClassPathResource;
import com.lockerz.service.commons.client.ClientException;
import com.lockerz.service.commons.utilities.ResultMessage;
import org.springframework.http.converter.HttpMessageConverter;
import com.lockerz.service.user.utilities.PlaceholderConfigurer;
import com.lockerz.service.commons.client.ClientResponseErrorHandler;
import com.lockerz.service.commons.client.ClientMappingJacksonHttpMessageConverter;

public class UserClientImpl implements Client {
	
	// need this
	public static final double BAD_ENDPOINT = 2000.01;
	
	// need these
	private RestTemplate restTemplate = null;
	private static UserClientImpl instance = null;
	private Properties properties = new Properties();
	
	// constructor
	protected UserClientImpl() {
		// create the context here
		this.restTemplate = new RestTemplate();
		// set the error handler
		restTemplate.setErrorHandler(new ClientResponseErrorHandler());
		// need the converters
		List<HttpMessageConverter<?>> converters =  new ArrayList<HttpMessageConverter<?>>();
		// add a custom converter here
        converters.add(new ClientMappingJacksonHttpMessageConverter());
        // set the converter here
        restTemplate.setMessageConverters(converters);
		// need this
		InputStream in = null;
		// try
		try {
			// get the properties file here
			in = new ClassPathResource(PlaceholderConfigurer.HANDLE + ".properties").getInputStream();
			// sanity check
			if(in != null) {
				// load here
	            properties.load(in);
			}
		// ignore here
		} catch(IOException e) {
		}
	}
	  
	// get the instance here
	public static UserClientImpl getInstance() {
		// sanity check
		if(instance == null) {
			// create the instance
	        instance = new UserClientImpl();
	    }
	    // return here
		return instance;
	}
	
	public String login(String token, String username, String password, String remoteIp) 
	throws ClientException {
		// need this
		String endpoint = getEndpoint("authenticate.endpoint");
		// need this
		Map<String, String> vars = new HashMap<String, String>();
		// set the user name
		vars.put("apiKey", token);
		// set the user name
		vars.put("username", username);
		// set the password
		vars.put("password", password);
		// set the password
		vars.put("remoteIp", remoteIp);
		// need this
		ResponseEntity<Object> response = null;
		// need this
		String message = null;
		// try
		try {
			// get the results here
			response = (ResponseEntity<Object>) restTemplate.getForEntity(endpoint, Object.class, vars);
		// catch and ignore
		} catch(Exception e) {
			// get the message
			message = e.getMessage();
		}
		// validate the response
		if(response != null && response.getStatusCode() == HttpStatus.OK) {
			// suppress here
			@SuppressWarnings("unchecked")
			// need this
			Map<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
			// return the token here
			return (String) map.get("token");
		// handle here
		} else {
			// handle the response here
			throw buildResponseException(response, message);
		}
	}
	
	public String getEndpoint(String key) throws ClientException{
		// sanity check
		if(properties.getProperty(key) != null) {
			// set the end point
			return properties.getProperty(key);
		// throw 
		} else {
			// need this
			String message = "unable to find endpoint " + key;
			// need this
			HashMap<Double, String> messages = new HashMap<Double, String>(1);
			// put the error
			messages.put(BAD_ENDPOINT, message);
			// create the exception
			throw new ClientException(message, messages, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ClientException buildResponseException(ResponseEntity<Object> response, String message) {
		// sanity check
		if(response != null) {
			// need this
			Map<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
			// create here
			ResultMessage resultMessage = new ResultMessage((Map<String, Object>) map.get("body"));
			// need this
			message = resultMessage.getMessage();
			// need this
			HashMap<Double, String> messages = resultMessage.getMessages();
			// throw the exception here
			return new ClientException(message, messages, response.getStatusCode());
		// caught previous
		} else {
			// throw the exception here
			return new ClientException(message);
		}
	}
}
