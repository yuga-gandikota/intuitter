package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base exception wrapper that annotates HTTP error code so that we can return back to the client
 * 
 * ex: UserNotFoundException will be mapped to 404. 
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")  // 500
public class BaseRestException extends Exception {

	public BaseRestException() {
	}
	
	public BaseRestException(String message) {
	}
	
	public BaseRestException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
