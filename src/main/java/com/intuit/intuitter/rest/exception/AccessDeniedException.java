package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to represent unauthorized access.
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
public class AccessDeniedException extends BaseRestException {
	
	public AccessDeniedException() {
		setStatusCode(HttpStatus.UNAUTHORIZED);
	}
	
	public AccessDeniedException(String message) {
		super(message);
		setStatusCode(HttpStatus.UNAUTHORIZED);
	}
	
	public AccessDeniedException(String message, Throwable throwable) {
		super(message, throwable);
		setStatusCode(HttpStatus.UNAUTHORIZED);
	}
}
