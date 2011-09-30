package com.lockerz.service.user.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.user.services.ServiceImpl;
import com.lockerz.service.user.services.ServiceException;
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
	private ServiceImpl service = null;
	
	@Autowired
    public void setService(ServiceImpl service) {
        // set the point service here
        this.service = service;
    }
	
	@RequestMapping(value="/authenticate", method = RequestMethod.GET)
    public ResponseEntity<UserModel> authenticate(String username, String password, String remoteIp) 
    throws ServiceControllerException {
		// try
		try {
			// get the user here
			UserModel userModel = service.authenticate(username, password, remoteIp);
			// sanity check
			if(userModel != null) {
				// return here
				return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
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
