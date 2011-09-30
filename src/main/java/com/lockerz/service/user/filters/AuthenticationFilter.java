package com.lockerz.service.user.filters;

import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lockerz.service.commons.auth.Authenticator;
import com.lockerz.service.user.utilities.RestException;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationFilter extends GenericFilterBean {
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	// need these
	public static final String AUTHENTICATION_HEADER = "Authentication";
	public static final String AUTHORIZATION_HEADER = "Authorization";
    
    // need these
	public static final String MESSAGE = "message";
	public static final String STATUS_CODE = "statusCode";
	
	// need this
	@SuppressWarnings("unused")
	private Authenticator authenticator;
   
	public void setAuthenticator(Authenticator authenticator) {
		// set the authenticator here
		this.authenticator = authenticator;
	}
    
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) 
    throws IOException, ServletException {
    	// cast the request
        HttpServletRequest request = (HttpServletRequest) sRequest;
        // cast the response
        HttpServletResponse response = (HttpServletResponse) sResponse;
        // get the key here
        String authentication = request.getHeader(AUTHENTICATION_HEADER);
        // sanity check
        if (authentication == null) {
        	// allow for injection here
        	authentication = request.getParameter(AUTHENTICATION_HEADER);
        }
        // sanity check
        if (authentication == null) {
        	// continue here
        	chain.doFilter(request, response);
        	// return here
        	return;
        // validate
        } else {
        	// get the key here
            String authorization = request.getHeader(AUTHORIZATION_HEADER);
            // sanity check
            if (authorization == null) {
            	// allow for injection here
            	authorization = request.getParameter(AUTHORIZATION_HEADER);
            }
            // sanity check
            if (authorization == null) {
            	// need this
    			String message = "unable to locate authorization";
    			// throw new exception here
        		throw new RestException(message, null, HttpStatus.UNAUTHORIZED);
            // validate
            } else {
            	
            }
        }
    }
}
