package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to represent "not found" case for user requests.
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class UserNotFoundException extends BaseRestException {
	
	public UserNotFoundException() {
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
