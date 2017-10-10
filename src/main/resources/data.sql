insert into user(id, name) values ('gauss', 'Carl Gauss');
insert into user(id, name) values ('euler', 'Leonhard Euler');
insert into user(id, name) values ('euclid', 'Euclid');


insert into follower(follower, followee, since) values ('gauss', 'euler', CURRENT_TIMESTAMP);
insert into follower(follower, followee, since) values ('euclid', 'euler', CURRENT_TIMESTAMP);
insert into follower(follower, followee, since) values ('euclid', 'gauss', CURRENT_TIMESTAMP);


insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #1 from user gauss', 'gauss', CURRENT_TIMESTAMP+1);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #2 from user gauss', 'gauss', CURRENT_TIMESTAMP+2);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #1 from user euler', 'euler', CURRENT_TIMESTAMP+3);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #2 from user euler', 'euler', CURRENT_TIMESTAMP+4);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #1 from user euclid', 'euclid', CURRENT_TIMESTAMP+5);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #2 from user euclid', 'euclid', CURRENT_TIMESTAMP+6);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #3 from user gauss', 'gauss', CURRENT_TIMESTAMP+7);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #4 from user gauss', 'gauss', CURRENT_TIMESTAMP+8);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #3 from user euler', 'euler', CURRENT_TIMESTAMP+9);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #4 from user euler', 'euler', CURRENT_TIMESTAMP+10);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #5 from user euler', 'euler', CURRENT_TIMESTAMP+11);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #3 from user euclid', 'euclid', CURRENT_TIMESTAMP+12);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #4 from user euclid', 'euclid', CURRENT_TIMESTAMP+13);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #5 from user euclid', 'euclid', CURRENT_TIMESTAMP+14);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #5 from user gauss', 'gauss', CURRENT_TIMESTAMP+15);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #6 from user gauss', 'gauss', CURRENT_TIMESTAMP+16);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #7 from user gauss', 'gauss', CURRENT_TIMESTAMP+17);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #6 from user euler', 'euler', CURRENT_TIMESTAMP+18);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #7 from user euler', 'euler', CURRENT_TIMESTAMP+19);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #8 from user euler', 'euler', CURRENT_TIMESTAMP+20);

insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #6 from user euclid', 'euclid', CURRENT_TIMESTAMP+21);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #7 from user euclid', 'euclid', CURRENT_TIMESTAMP+22);
insert into tweet (id, text, author, time) values (TWEET_ID_SEQ.NEXTVAL, 'Tweet message #8 from user euclid', 'euclid', CURRENT_TIMESTAMP+23);


