# Intuitter

Twitter like implmentation for Intuit employees. This demonstation is supposed to be integrated into an existing LDAP server.
I used forumsys's Online LDAP Test Server. It is free LDAP server. Although we can log into any accoun that is availbale on that server, I chose
to use three names guass, euler, euclid. Password is 'password'.

When you run the application, it makes the following RESTful services available.

You will have to be be logged in as one of the employees ('gauss', 'euclid', 'euler') to be able to access these servcies.
If you access any endpoing that you are not authorized to access, application

GET /api/v1/feed
  -- returns tweet feed of the current logged in user.
  
GET /api/v1/users/{employeeId}/feed[?page={pageNum}]
  -- returns tweet feed of the employee. Current implementation checks whether employeeId matches the current logged in user or not.
  	 But, we can extend it to more complex authorization model. This service takes an optional query string param "page". its default value is 0.
  	 page size is currently set to '5' (for testig purposes) 
  	  
GET /api/v1/users/{employeeId}/tweets[?page={pgeNum}]
  -- returns tweets sent by the employee with id 'employeeId'. Current implementation checks whether employeeId matches the current logged in user or not.
  	 But, we can extend it to more complex authorization model. ex: allow if current user is a follower of 'employeeId'


Few examples. Assuming that you are logged in as 'gauss' with password 'password'.

Request:
<code>GET /api/v1/users/gauss/feed</code>

Response:
<code>
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
</code>


Request:
<code>GET /api/v1/users/euclid/feed?page=1</code>


Response:
<code>
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
</code>


## Deployment

This applicatin is built using [Spring Boot](https://spring.io/guides/gs/spring-boot/). Please refer to its documentation about how to run the application.


## Built With

* [Spring Boot](https://spring.io/guides/gs/spring-boot/) - The Spring framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Yuga Gandikota** - *Initial work* - [yuga-gandikota](https://github.com/yuga-gandikota)
