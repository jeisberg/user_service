package com.lockerz.service.user;

import junit.framework.TestCase;
import org.springframework.http.HttpStatus;
import com.lockerz.service.user.models.UserModel;
import com.lockerz.service.user.client.UserClientImpl;
import com.lockerz.service.commons.client.ClientException;

public class ClientTest extends TestCase {
	
	public void test() {
        // need this
        UserModel user = null;
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("ometoken", "jeisberg500@yahoo.com", "abc123..", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        } catch(Exception e) {
        	// out here
        	System.out.println("EXCEPTION: " + e.getMessage());
        }
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("sometoken", "eisberg500@yahoo.com", "abc123..", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        } catch(Exception e) {
        	// out here
        	System.out.println("EXCEPTION: " + e.getMessage());
        }
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("sometoken", "eisberg500@yahoo.com", "abc123..", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        } catch(Exception e) {
        	// out here
        	System.out.println("EXCEPTION: " + e.getMessage());
        }
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("sometoken", "jeisberg500@yahoo.com", "abc123.", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.UNAUTHORIZED);
        } catch(Exception e) {
        	// out here
        	System.out.println(e.getMessage());
        }
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("sometoken", "jeisberg500@yahoo.com", "", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
        	// out here
        	System.out.println(e.getMessage());
        }
        // test the service
        try { 
        	// get the data here
    		user = UserClientImpl.getInstance().authenticate("sometoken", "", "abc123..", "blah");
        	// output here
        	System.out.println(user);
        // catch here
        } catch(ClientException e) {
        	// out here
        	System.out.println(e.getMessage());
        	// out here
        	System.out.println(e.getMessages());
        	// out here
        	System.out.println(e.getHttpStatus());
        	// assert here
        	assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
        	// out here
        	System.out.println(e.getMessage());
        }
        // exit here
    	System.exit(0);
    }
}