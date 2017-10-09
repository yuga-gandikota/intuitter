package com.intuit.intuitter.rest.contoller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import com.intuit.intuitter.rest.exception.AccessDeniedException;\
import org.springframework.security.access.AccessDeniedException;

/**
 * Base class for all REST resources. Contains utility methods to validate requests. 
 *  
 * @author Yuga Gandikota
 */
public class RestBaseV1 {
	
	/* set to false for now, as the embedded LDAP setup didn't work.*/
	@Value("#{'${skip.authorization.check}'}")
	boolean skipAuthorizationCheck = false;
	
	/* Check if the current user is authorized for the current request or not */
	public void authorizeForUser(String employeeId, Principal principal) 
	throws AccessDeniedException {
		String employeeIdInSession = getEmployeedIdFromPrincipal(principal);
		
		if (!skipAuthorizationCheck && !employeeId.equals(employeeIdInSession)) {
			throw new AccessDeniedException("Unauthorized requet to data of user:"+employeeId);
		}
	}

	private String getEmployeedIdFromPrincipal(Principal principal) {
		return principal.getName();
	}
	
}
