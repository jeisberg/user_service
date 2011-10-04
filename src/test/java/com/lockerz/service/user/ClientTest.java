package com.lockerz.service.user;

import junit.framework.TestCase;

import org.springframework.http.HttpStatus;

import com.lockerz.service.commons.client.ClientException;
import com.lockerz.service.user.client.UserClientImpl;

public class ClientTest extends TestCase {
	
	public void test() {
        // need this
        String userToken = null;
        // test the service
        try { 
        	// get the data here
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "srijith@lockerz.com", "lockerz", "blah");
        	// output here
        	System.out.println(userToken);
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
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "eisberg500@yahoo.com", "abc123..", "blah");
        	// output here
        	System.out.println(userToken);
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
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "srijith@lockerz.com", "abc123..", "blah");
        	// output here
        	System.out.println(userToken);
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
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "srijith@lockerz.com", "bad...", "blah");
        	// output here
        	System.out.println(userToken);
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
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "srijith@lockerz.com", "", "blah");
        	// output here
        	System.out.println(userToken);
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
    		userToken = UserClientImpl.getInstance().login("A1234567890B", "", "abc123..", "blah");
        	// output here
        	System.out.println(userToken);
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