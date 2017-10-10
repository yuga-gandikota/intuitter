package com.intuit.intuitter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intuit.intuitter.rest.exception.BaseRestException;

@ControllerAdvice
public class RestExeptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BaseRestException.class)
    protected ResponseEntity<String> handleBaseRestException(BaseRestException e) {
    	return new ResponseEntity<String>(e.getMessage(), e.getStatusCode());
    }    
}
