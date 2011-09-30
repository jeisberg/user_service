package com.lockerz.service.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;

import com.lockerz.service.user.auth.AuthenticatorException;
import com.lockerz.service.user.auth.TokenAuthenticatorImpl;
import com.lockerz.service.user.models.TokenAuthModel;
import com.lockerz.service.user.services.ServiceException;

import junit.framework.TestCase;

public class AuthenticationTest extends TestCase
{
    public void test()
    {

        // get the context here
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        TokenAuthenticatorImpl tokenAuthenticator = (TokenAuthenticatorImpl) context.getBean("tokenAuthenticator");
        
        try
        {
            long uid = tokenAuthenticator.authenticate("testkey", "testToken");
            
            assertEquals(uid, 25);
        
        } catch(AuthenticatorException ae)
        {
            // out here
            System.out.println(ae.getMessage());
            // out here
            System.out.println(ae.getMessages());
            // out here
            System.out.println(ae.getHttpStatus());
            // assert here
            assertEquals(ae.getHttpStatus(), HttpStatus.FORBIDDEN);
            
        }catch(ServiceException e) {
            // out here
            System.out.println(e.getMessage());
            // out here
            System.out.println(e.getMessages());
            // out here
            System.out.println(e.getHttpStatus());
            // assert here
            assertEquals(e.getHttpStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        System.exit(0);
    }

}
