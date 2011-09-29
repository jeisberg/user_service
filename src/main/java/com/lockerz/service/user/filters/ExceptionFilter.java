package com.lockerz.service.user.filters;

import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import org.codehaus.jackson.map.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.GenericFilterBean;
import com.lockerz.service.user.utilities.RestException;
import com.lockerz.service.user.utilities.ResultMessage;

public class ExceptionFilter extends GenericFilterBean {
	
	// need these
	public static final String MESSAGE = "message";
	public static final String STATUS_CODE = "statusCode";
	
	// need this
	public static final String CONTENT_TYPE = "application/json";
	
	// create the logger here
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(ExceptionFilter.class);
    
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) 
    throws IOException, ServletException {
    	// cast the request
        HttpServletRequest request = (HttpServletRequest)sRequest;
        // cast the response
        HttpServletResponse response = (HttpServletResponse)sResponse;
        // try
        try {
        	// do the chain everybody
            chain.doFilter(request, response);
        // catch the goodies here
        } catch (RestException e) {
        	// try
            try {
            	// set the status
            	response.setStatus(e.getHttpStatus().value());
            	// set the response type
            	response.setContentType(CONTENT_TYPE);
            	// need this
    			ResultMessage resultMessage = new ResultMessage(e.getMessage(), e.getMessages());
    			// set the error here
    			ResponseEntity<ResultMessage> error = 
    				// instance created here
    				new ResponseEntity<ResultMessage>(resultMessage, e.getHttpStatus());
                // write the value
                new ObjectMapper().writeValue(response.getWriter(), error);
            // catch here
            } catch (IOException ioe) {
            }
        }
    }
}
