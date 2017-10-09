create table user (
  id VARCHAR(64) PRIMARY KEY,
  name VARCHAR(64) NOT NULL
);

create table tweet (
  id BIGINT PRIMARY KEY,
  text VARCHAR(140) NOT NULL,
  author VARCHAR(64) NOT NULL,
  time DATE NOT NULL,
  
  FOREIGN KEY(author) REFERENCES user(id)
);

create table follower (
  follower VARCHAR(64) NOT NULL,
  followee VARCHAR(64) NOT NULL,
  since DATE NOT NULL,
  
  FOREIGN KEY(follower) REFERENCES user(id),
  FOREIGN KEY(followee) REFERENCES user(id)

);

create sequence TWEET_ID_SEQ;
