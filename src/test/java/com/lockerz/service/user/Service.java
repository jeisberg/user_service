package com.lockerz.service.user;

import junit.framework.TestCase;
import org.springframework.http.HttpStatus;
import com.lockerz.service.user.models.UserProfile;
import org.springframework.context.ApplicationContext;
import com.lockerz.service.user.services.UserServiceImpl;
import com.lockerz.service.commons.services.ServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Service extends TestCase {

	public void test() {
    	// get the context here
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        // get the template service here
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        // need this
        String userToken = null;
        // test the service
        try { 
        	// get the user lookup
        	userToken = userService.login("A1234567890B", "srijith@lockerz.com", "lockerz", "");
        	// output here
        	System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	//assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        }
        // test the service
        try { 
        	// get the user lookup
        	userToken = userService.login("A1234567890B", "srijith@lockerz.com", "baddd", "");
        	// output here
        	System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        //	assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        }
        // test the service
        try { 
        	// get the user lookup
        	userToken = userService.login("A1234567890B", "srijith@lockerz.com", "", "");
        	// output here
        	System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
        }
        // test the service
        try { 
        	// get the user lookup
        	userToken = userService.login("A1234567890B", "", "abc123..", "");
        	// output here
        	System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
        }
        // test the service
        try { 
        	// get the user lookup
        	userToken = userService.login("A1234567890B","", "", "");
        	// output here
        	System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
        }
        
        // test the service
        try { 
            // get the user lookup
            userToken = userService.login("invalid Key","srijith@lockerz.com", "lockerz", "");
            // output here
            System.out.println(userToken);
        // catch here
        } catch(ServiceException e) {
            // out here
            System.out.println(e.getMessage());
            // out here
            System.out.println(e.getMessages());
            // out here
            System.out.println(e.getHttpStatus());
            // assert here
            assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        }
        
        // test the service
        try { 
            // get the user lookup
            UserProfile userProfile = userService.profile(16223374L);
            // output here
            System.out.println(userProfile);
        // catch here
        } catch(ServiceException e) {
            // out here
            System.out.println(e.getMessage());
            // out here
            System.out.println(e.getMessages());
            // out here
            System.out.println(e.getHttpStatus());
            // assert here
            assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        }
        
        // exit here
    	System.exit(0);
    }
}
