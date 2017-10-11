package com.intuit.intuitter.rest.contoller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.intuitter.rest.exception.AccessDeniedException;
import com.intuit.intuitter.rest.exception.UserNotFoundException;
import com.intuit.intuitter.rest.model.Tweet;
import com.intuit.intuitter.rest.model.User;
import com.intuit.intuitter.rest.repository.TweetRepository;
import com.intuit.intuitter.rest.repository.UserRepository;

/**
 * Represents resource Tweets.
 * 
 * @author Yuga Gandikota
 */

@RestController
public class TweetsV1 extends RestBaseV1 {
	
	@Value("#{'${feed.default.page.size}'}")
	public Integer pageSize;
	
	@Autowired 
	TweetRepository tweetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = Logger.getLogger(TweetsV1.class);

	
	/**
	 * Fetches tweet feed of the currently logged-in user
	 * 
	 * @param employeeId, injected by spring, based on the request URI
	 * @param page injected by Spring, page number requested by the client.
	 * @param principal injected by Spring, current authenticated user info.
	 * @return
	 * @throws UserNotFoundException
	 * @throws AccessDeniedException 
	 */
	@RequestMapping(value="api/v1/feed")
	public List<Tweet> fetchCurrentUserFeed(@RequestParam(name="page", required=false, defaultValue="0") Integer page,
									Principal principal) 
	throws UserNotFoundException, AccessDeniedException {
		if (principal == null) {
			throw new AccessDeniedException("current user is not defined.");
		}
		String employeeId = principal.getName();
		
		User u = userRepository.findById(employeeId);
		if (u == null) {
			throw new UserNotFoundException ("employee with id:"+employeeId+" not found.");
		}
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "time");
		List<Tweet> tweets = tweetRepository.findByFollower(u, pageRequest);
		return tweets;
	}	
	/**
	 * Fetches tweet feed of the given user based on tweets from all of this user's followees.
	 * 
	 * @param employeeId, injected by spring, based on the request URI
	 * @param pageRequest injected by Spring, pagination info is specified by the client.
	 * @param principal injected by Spring, current authenticated user info.
	 * @return
	 * @throws UserNotFoundException
	 * @throws AccessDeniedException 
	 */
	@RequestMapping(value="api/v1/users/{employeeId}/feed")
	public List<Tweet> fetchUserFeed(@PathVariable("employeeId") String employeeId,
									@RequestParam(name="page", required=false, defaultValue="0") Integer page,
									Principal principal) 
	throws UserNotFoundException, AccessDeniedException {
		
		authorizeForUser(employeeId, principal);
		
		User u = userRepository.findById(employeeId);
		if (u == null) {
			throw new UserNotFoundException ("employee with id:"+employeeId+" not found.");
		}
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "time");
		List<Tweet> tweets = tweetRepository.findByFollower(u, pageRequest);
		return tweets;
	}
	
	
	/**
	 * Fetches messages tweeted by a user.
	 * 
	 * @param employeeId, injected by spring, based on the request URI
	 * @param pageRequest injected by Spring, pagination info is specified by the client.
	 * @param principal injected by Spring, current authenticated user info.
	 * @return
	 * @throws UserNotFoundException
	 * @throws AccessDeniedException 
	 */
	@RequestMapping(value="api/v1/users/{employeeId}/tweets")
	public List<Tweet> fetchTweets(@PathVariable("employeeId") String employeeId,
									@RequestParam(name="page", required=false, defaultValue="0") Integer page,
									Principal principal) 
	throws UserNotFoundException, AccessDeniedException {
		
		authorizeForUser(employeeId, principal);

		User u = userRepository.findById(employeeId);
		if (u == null) {
			throw new UserNotFoundException ("employee with id:"+employeeId+" not found.");
		}		
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "time");
		List<Tweet> tweets = tweetRepository.findByAuthor(u, pageRequest);
		return tweets;
	}	
}
