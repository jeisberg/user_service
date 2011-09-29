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
import com.lockerz.service.user.auth.Authorizer;
import org.springframework.web.filter.GenericFilterBean;
import com.lockerz.service.user.utilities.RestException;
import com.lockerz.service.user.auth.AuthorizerException;

public class AuthorizationFilter extends GenericFilterBean {
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);
    
	// need this
    private static final String AUTHORIZATION_HEADER = "Authorization";
    
    // need these
	public static final String MESSAGE = "message";
	public static final String STATUS_CODE = "statusCode";
    
    // need this
    private Authorizer authorizer;
    
    // set the authorization
    public void setAuthorizer(Authorizer authorizer) {
    	// set the authorization
    	this.authorizer = authorizer;
    }
    
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) 
    throws IOException, ServletException {
    	// cast the request
        HttpServletRequest request = (HttpServletRequest) sRequest;
        // cast the response
        HttpServletResponse response = (HttpServletResponse) sResponse;
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
        	// try
    		try {
    			// authenticate here
    			authorizer.authorize(authorization, -1);
    			// do the filter
    			chain.doFilter(request, response);
    		// catch here
    		} catch(AuthorizerException e) {
    			// need this
    			String message = this.getClass().getName() + " -> " + e.getMessage();
    			// throw new exception here
        		throw new RestException(message, e.getMessages(), e.getHttpStatus());
    		}
        }
    }
}
