package com.intuit.intuitter.rest.contoller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.intuitter.rest.exception.AccessDeniedException;
import com.intuit.intuitter.rest.model.User;
import com.intuit.intuitter.rest.repository.UserRepository;

/**
 * Represents REST resource User. Added version "v1" to allow for future changes.
 * 
 * @author Yuga Gandikota
 */

@RestController
@RequestMapping("api/v1/users")
public class UserV1 extends RestBaseV1 {
	
	@Autowired 
	UserRepository userRepository;
	
	@RequestMapping(value="/{employeeId}", method = RequestMethod.GET)
	public User fetchUser(@PathVariable("employeeId") String employeeId,
							Principal principal) 
	throws AccessDeniedException {

		authorizeForUser(employeeId, principal);
		
		User u = userRepository.findById(employeeId);
		return u;
	}
}
