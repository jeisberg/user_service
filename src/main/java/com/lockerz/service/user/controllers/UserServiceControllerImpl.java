package com.lockerz.service.user.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.lockerz.service.user.models.UserModelImpl;
import com.lockerz.service.user.services.ServiceImpl;
import com.lockerz.service.user.utilities.RestException;
import com.lockerz.service.user.services.ServiceException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping(value="/user")
public class UserServiceControllerImpl extends ServiceControllerImpl {
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(UserServiceControllerImpl.class);

	@Autowired
    public void setService(ServiceImpl service) {
        // set the point service here
        this.service = service;
    }
	
	@RequestMapping(value="/authenticate", method = RequestMethod.GET)
    public ResponseEntity<UserModelImpl> authenticate(String username, String password, String remoteIp) 
    throws RestException {
		// try
		try {
			// get the user here
			UserModelImpl userModelImpl = service.authenticate(username, password, remoteIp);
			// sanity check
			if(userModelImpl != null) {
				// return here
				return new ResponseEntity<UserModelImpl>(userModelImpl, HttpStatus.OK);
			// no user
			} else {
				// throw a rest exception here
				throw new RestException(null, null, HttpStatus.NOT_FOUND);
			}
		// catch here
		} catch(ServiceException e) {
			// throw a rest exception here
			throw new RestException(e.getMessage(), e.getMessages(), e.getHttpStatus());
		}
    } 
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
    public ResponseEntity<UserModelImpl> test(@Principal ServicePrincipal user) 
    throws RestException {
		// try
		try {
			if(true)
				throw new RestException(user.getName(), null, null);
		// catch here
		} catch(ServiceException e) {
			// throw a rest exception here
			throw new RestException(e.getMessage(), e.getMessages(), e.getHttpStatus());
		}
    } 
}
