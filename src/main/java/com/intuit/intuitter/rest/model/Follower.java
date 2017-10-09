package com.intuit.intuitter.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents "follower" relationship based on "follower" table.
 * If user A is following user B. We will have an entry in this table with A as follower and B as followee
 * 
 * @author Yuga Gandikota
 */
@Entity
@Table(name="follower")
public class Follower implements Serializable {
	private static final long serialVersionUID = -3779159607559799157L;

	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "followee")
	User followee;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "follower")
	User follower;
	
	Date since;
}
