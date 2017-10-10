package com.intuit.intuitter.rest.contoller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Value;
import com.intuit.intuitter.rest.exception.AccessDeniedException;

/**
 * Base class for all REST resources. Contains utility methods to validate requests. 
 *  
 * @author Yuga Gandikota
 */
public class RestBaseV1 {
	
	/* we can skip authorization check if the LDAP setup isnt working.*/
	@Value("#{'${skip.authorization.check}'}")
	boolean skipAuthorizationCheck = false;
	
	/* Check if the current user is authorized for the current request or not */
	public void authorizeForUser(String employeeId, Principal principal) 
	throws AccessDeniedException {
		if (!skipAuthorizationCheck) {
			String employeeIdInSession = getEmployeedIdFromPrincipal(principal);
			if (!employeeId.equals(employeeIdInSession)) {
				throw new AccessDeniedException("Unauthorized request for user data. user:"+employeeId);
			}
		}
	}

	private String getEmployeedIdFromPrincipal(Principal principal) {
		return principal.getName();
	}
	
}
