package com.lockerz.service.user;

import junit.framework.TestCase;
import org.springframework.http.HttpStatus;
import com.lockerz.service.user.models.UserModel;
import org.springframework.context.ApplicationContext;
import com.lockerz.service.user.services.UserServiceImpl;
import com.lockerz.service.commons.services.ServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest extends TestCase {

	public void test() {
    	// get the context here
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        // get the template service here
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        // need this
        UserModel user = null;
        // test the service
        try { 
        	// get the user lookup
        	user = userService.login("eisberg500@yahoo.com", "abc123.", "");
        	// output here
        	System.out.println(user);
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
        	user = userService.login("jeisberg500@yahoo.com", "abc123.", "");
        	// output here
        	System.out.println(user);
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
        	user = userService.login("jeisberg500@yahoo.com", "", "");
        	// output here
        	System.out.println(user);
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
        	user = userService.login("", "abc123..", "");
        	// output here
        	System.out.println(user);
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
        	user = userService.login("", "", "");
        	// output here
        	System.out.println(user);
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
        // exit here
    	System.exit(0);
    }
}
