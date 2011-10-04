package com.lockerz.service.user.controllers;

import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.lockerz.service.user.utilities.Utilities;
import com.lockerz.service.user.services.ServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;
import com.lockerz.service.commons.services.ServiceException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lockerz.service.commons.controllers.ServiceControllerException;

@Controller @RequestMapping(value="/user")
public class UserServiceController {
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(UserServiceController.class);

	// need this
	private ServiceImpl	service = null;
	
	@Autowired
    public void setService(ServiceImpl service) {
        // set the point service here
        this.service = service;
    }
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> login(HttpServletRequest request, @RequestParam String username, @RequestParam String password, @RequestParam String remoteIp) 
    throws ServiceControllerException {
		// try
		try {
		    // get the authorization key here
		    String apiKey = Utilities.getAuthorizationKey(request);
		    // sanity check
            if(apiKey == null) {
            	// need this
            	String message = "Cannot find authorization key";
            	// throw the exception here and go
                throw new ServiceControllerException(message, null, HttpStatus.UNAUTHORIZED);
            }
			// get the user here
			String token = service.login(apiKey, username, password, remoteIp);
			// sanity check
			if(token != null) {
				// return here
			    Map<String,String> result = new HashMap<String, String>();
			    // put the result here
			    result.put("token", token);
			    // return the token here and go
				return new ResponseEntity<Map<String,String>>(result, HttpStatus.OK);
			// no user
			} else {
				// throw a rest exception here
				throw new ServiceControllerException(null, null, HttpStatus.NOT_FOUND);
			}
		// catch here
		} catch(ServiceException e) {
			// throw a rest exception here
			throw new ServiceControllerException(e.getMessage(), e.getMessages(), e.getHttpStatus());
		}
    }
}
