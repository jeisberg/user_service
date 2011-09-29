package com.lockerz.service.user.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ClientResponseErrorHandler implements ResponseErrorHandler {

    public void handleError(ClientHttpResponse response) throws IOException {
    }
    
    public boolean hasError(ClientHttpResponse response) throws IOException {
    	// return here
    	return false;
    }
} 
