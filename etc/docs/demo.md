# Intuitter Demo

Twitter like implementation for Intuit employees. This demonstration is supposed to be integrated into an existing LDAP server. I used [Forum System's Online LDAP Test Server](http://www.forumsys.com/tutorials/integration-how-to/ldap/online-ldap-test-server/). It is a free read-only LDAP server. Although we can log into any account that is available on that server, I chose to use three names `gauss`, `euler`, `euclid`. Password is `password`. I have setup initial data for these three users.  Refer to [/src/main/resources/schema.sql](https://github.com/yuga-gandikota/intuitter/blob/master/src/main/resources/schema.sql) and [/src/main/resources/data.sql](https://github.com/yuga-gandikota/intuitter/blob/master/src/main/resources/data.sql) for more details about initial data.

When you run the application, it makes the following RESTful services available. You will have to be be logged in as one of the employees (`gauss`, `euclid`, `euler`) to be able to access these services. If you access any endpoint that you are not authorized to access, application returns a `401 unauthorized` response.


## Assumptions
Following assumptions were made to keep implementation and its testing simple:
* I have set the page size for the tweet feed as 5. Current implementation of pagination is very simple. For pagination to work effectively, we may have to use a reference point (like last synced time stamp or last seen tweet id or a combination of both). If the tweetId is setup to be sequential, we can probably use it as the reference point. Client app or the end client remembers the last tweet id that user has seen, and passes it along while making next fetch service call. We use that on the server side and return next 100 tweets with id > lastSeenTweetId.
* When user status is not logged in (before first login), for first request to any of the RESTful services application redirects user to login page instead of a typical RESTful response of sending 401 error code.
* After successful login, subsequent unauthorized requests will return 401 error code.
* It is usually a good practice to add some unit testing. Due of time constraints I didn't add any.

## Service API implemented

* Following service returns tweet feed of the current logged in user.
```
GET /api/v1/feed
```

* Following service returns tweet feed for 'employeeId'. Current implementation checks whether employeeId matches the current logged in user or not. But, we can extend it to more complex authorization model. This service takes an optional query string param "page". Its default value is 0. Page size is currently set to '5' (for testing purposes)
```
GET /api/v1/users/{employeeId}/feed[?page={pageNum}]
```

* Following service returns tweets sent by the employee with id 'employeeId'. Current implementation checks whether employeeId matches the current logged in user or not. But, we can extend it to more complex authorization model. ex: allow if current user is a follower of 'employeeId'
```	  
GET /api/v1/users/{employeeId}/tweets[?page={pageNum}]
```
## Examples
Few examples. Assuming that you are logged in as 'gauss' with password 'password'.

### Example 1:
Fetch tweet feed for user 'gauss'

Request:
```
GET /api/v1/users/gauss/feed
```

Response:
```
[  
   {  
      "id":15,
      "text":"Tweet message #8 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1508137200000
   },
   {  
      "id":14,
      "text":"Tweet message #7 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1508050800000
   },
   {  
      "id":7,
      "text":"Tweet message #7 from user gauss",
      "author":{  
         "id":"gauss",
         "name":"Carl Gauss"
      },
      "time":1508050800000
   },
   {  
      "id":13,
      "text":"Tweet message #6 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1507964400000
   },
   {  
      "id":6,
      "text":"Tweet message #6 from user gauss",
      "author":{  
         "id":"gauss",
         "name":"Carl Gauss"
      },
      "time":1507964400000
   }
]
```

### Example 2:
Fetch second page of tweet feed for user 'gauss'

Request:
```
GET /api/v1/users/euclid/feed?page=1
```

Response:
```
[  
   {  
      "id":5,
      "text":"Tweet message #5 from user gauss",
      "author":{  
         "id":"gauss",
         "name":"Carl Gauss"
      },
      "time":1507878000000
   },
   {  
      "id":12,
      "text":"Tweet message #5 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1507878000000
   },
   {  
      "id":4,
      "text":"Tweet message #4 from user gauss",
      "author":{  
         "id":"gauss",
         "name":"Carl Gauss"
      },
      "time":1507791600000
   },
   {  
      "id":11,
      "text":"Tweet message #4 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1507791600000
   },
   {  
      "id":10,
      "text":"Tweet message #3 from user euler",
      "author":{  
         "id":"euler",
         "name":"Leonhard Euler"
      },
      "time":1507705200000
   }
]
```

## Deployment

This application is built using [Spring Boot](https://spring.io/guides/gs/spring-boot/). Please refer to its documentation about how to run the application.
*NOTE:* If you are unable to reach Forum System's LDAP server (may happen when you are in corporate network), you can skip authentication by setting the following in `\src\main\resources\application.properties`
```
#disable authentication/authorization check in controllers.
skip.authorization.check=true
```

## Built With

* [Spring Boot](https://spring.io/guides/gs/spring-boot/) - The Spring framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Forum Systems](http://www.forumsys.com/tutorials/integration-how-to/ldap/online-ldap-test-server/) - LDAP server used for testing.
* [GitHub][https://github.com/]

## Authors

* **Yuga Gandikota** - *ongoing work :)* - [yuga-gandikota](https://github.com/yuga-gandikota)
