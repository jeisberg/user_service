package com.lockerz.service.user.filters;

import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import com.lockerz.service.user.auth.Authenticator;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationFilter extends GenericFilterBean {
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);
    
    // need these
	public static final String MESSAGE = "message";
	public static final String STATUS_CODE = "statusCode";
	
	// need this
    private Authenticator authenticator;
   
	public void setAuthenticator(Authenticator authenticator) {
		// set the authenticator here
		this.authenticator = authenticator;
	}
    
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) 
    throws IOException, ServletException {
    	// sanity check
    	if(true) {
    		// throw here
    		throw new IOException("test");
    	}
    }
}
