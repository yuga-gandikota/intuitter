package com.intuit.intuitter.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Represents Tweet entity.
 * 
 * @author Yuga Gandikota
 */

@Entity
@Table(name="tweet")
public class Tweet {
	
	@Id
	@SequenceGenerator(name="TWEET_SEQ", sequenceName="TWEET_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TWEET_SEQ")
	/*id of this tweet*/
	long id;
	
	/*content of this tweet*/
	String text;
	
	/*employee id of the author of this tweet*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "author")
	User author;
	
	/*timestamp of this tweet when it was sent (more precisely the server side timestamp)*/
	private Date time;
	
	@Override
	public String toString() {
		return String.format("Tweet[id:%d, author:%d, time:%s, text:%s]", id, (author==null ? "-" : author.getId()), getTime().toString(), text);
	}
	

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
