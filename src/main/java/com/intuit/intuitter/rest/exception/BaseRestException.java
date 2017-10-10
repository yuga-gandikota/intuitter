package com.intuit.intuitter.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * Base exception wrapper that annotates HTTP error code so that we can return back to the client
 * 
 * ex: UserNotFoundException will be mapped to 404. 
 * 
 * @author Yuga Gandikota
 */
@SuppressWarnings("serial")
public class BaseRestException extends Exception {
	private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

	public BaseRestException() {
	}
	
	public BaseRestException(String message) {
		super(message);
	}
	
	public BaseRestException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
