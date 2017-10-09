package com.intuit.intuitter.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.intuit.intuitter.rest.model.Tweet;
import com.intuit.intuitter.rest.model.User;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
	
	/* Fetches tweets from an author */
    List<Tweet> findByAuthor(User author, Pageable pageRequest);
    
    /* Fetches tweet feed of a given user.*/
    @Query(value="SELECT t FROM Tweet t WHERE t.author IN (SELECT f.followee FROM Follower f WHERE f.follower = :follower)")
    List<Tweet> findByFollower(@Param("follower") User follower, Pageable pageRequest);
}
