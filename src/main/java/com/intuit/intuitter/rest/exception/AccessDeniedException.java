package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to represent unauthorized access.
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized request")
public class AccessDeniedException extends BaseRestException {
	
	public AccessDeniedException() {
	}
	
	public AccessDeniedException(String message) {
		super(message);
	}
	
	public AccessDeniedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
