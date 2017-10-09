package com.intuit.intuitter.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.intuit.intuitter.rest.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	/* finds user by id*/
	User findById(String id);
}
