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
import com.lockerz.service.user.models.UserModel;
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
	public static final double BAD_ENDPOINT = 100.02;
	
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
	
	public UserModel login(String token, String username, String password, String remoteIp) 
	throws ClientException {
		// need this
		String endpoint = null;
		// sanity check
		if(properties.getProperty("authenticate.endpoint") != null) {
			// set the end point
			endpoint = properties.getProperty("authenticate.endpoint");
		} else {
			// need this
			String message = "unable to find endpoint for authenticate service";
			// create the exception
			throw new ClientException(message, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// need this
		Map<String, String> vars = new HashMap<String, String>();
		// set the user name
		vars.put("token", token);
		// set the user name
		vars.put("username", username);
		// set the password
		vars.put("password", password);
		// set the password
		vars.put("remoteIp", remoteIp);
		// get the results here
		ResponseEntity<Object> response = (ResponseEntity<Object>) restTemplate.getForEntity(endpoint, Object.class, vars);
		// suppress here
		@SuppressWarnings("unchecked")
		// need this
		Map<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		// validate the response
		if(response.getStatusCode() == HttpStatus.OK) {
			// return here
			return new UserModel(map);
		// handle here
		} else {
			// suppress here
			@SuppressWarnings("unchecked")
			// create here
			ResultMessage resultMessage = new ResultMessage((Map<String, Object>) map.get("body"));
			// need this
			String message = resultMessage.getMessage();
			// need this
			HashMap<Double, String> messages = resultMessage.getMessages();
			// throw the exception here
			throw new ClientException(message, messages, response.getStatusCode());
		}
	}
}
