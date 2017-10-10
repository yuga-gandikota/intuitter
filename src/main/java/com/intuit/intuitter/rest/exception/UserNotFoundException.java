package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to represent "not found" case for user requests.
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends BaseRestException {
	
	public UserNotFoundException() {
		setStatusCode(HttpStatus.NOT_FOUND);
	}
	
	public UserNotFoundException(String message) {
		super(message);
		setStatusCode(HttpStatus.NOT_FOUND);
	}
	
	public UserNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
		setStatusCode(HttpStatus.NOT_FOUND);
	}
}
