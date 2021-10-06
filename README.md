# pwd-mgmt-api
-- Api to manage password reset--

Requirement/Scenario/Usecase:
This Restful API is called to reset the password by providing Email, New Password, Confirm Password in the request. Below valdiations are performed on the input request and appropriate error messages are returned from the service.

	a. Valid user email input
	b. If this is an existing user
	c. If the new password and confirm password fields match
	
	On the successful validation of the input request, the password is encoded and updated in the database, and a 'Password Reset Event' is published on the ActiveMQ Queue

Technical Stack:
	Springboot (2.4.11)
	Java 11
	H2 in-memory database
	Active MQ
	Docker/Docker Compose
	
Pre-requisites:
	Docker/Docker Compose installed
	Postman or similar tool to run the restful service
	
Assumptions:
	- Soultion provided is just to support above usecase.
	- Currently no security aspects are added to keep the application simple.
	- The functionality of this API is just limited to the usecase mentioned above i.e this API currently has only one POST method that is used while resetting of password. This API do not create a new user, but this can be easily enhanced.
	- Although the any relational database can be used for this API, for simplicity, H2 im-memory database is used. Modifying to any other database is relatively easy by udpating relavant entry in the Docker compose.
	- Few data is inserted/loaded as a startup script. These email ids below can be used with the API to demonstrate successful Password reset functionality.
	    Ex:  'demo1@gtree.com', 'demo2@gtree.com', 'demo3@gtree.com',  
	- Email is assumed to be unique per user.
	- Current soultion is not enabled with API documentation support (SwaggerUI), but this can be enhanced easily. 
	- Data is not persisted across the restarts of the application (H2 in-memory database)

How to run application:

	- Checkout the code and click/execute file:   "run.sh" 	
	- Note: a- This script will Compile, Build, Create API Docker Image, Pull ActiveMQ Docker Image,  run both API Docker Image and ActiveMQ Image
			b- This API will run on port: 8085
			c- ActiveMQ admin run on port: 8161

Test Scenarios:
	1: New password and Confirm Password do not match
		Request:
	    {
			"email" : "demo2@gtree.com",
			"newPassword" : "123",
			"confirmPassword": "1231"
		}
		Response:
		{
			"timestamp": "2021-10-06T10:44:31.241+00:00",
			"status": 400,
			"error": "Bad Request",
			"message": "New password and confirmation password should match",
			"path": "/pwd-mgmt/v1/reset"
		}

	2: Email syntax is not valid
		Request:
		{
			 "email" : "demo2@gtree@com",
			 "newPassword" : "1231",
			 "confirmPassword": "1231"
		}
		Response:
		{
			"timestamp": "2021-10-06T10:49:10.517+00:00",
			"status": 400,
			"error": "Bad Request",
			"message": "Invalid email",
			"path": "/pwd-mgmt/v1/reset"
		}
		
	3. Email that do not match to an user in the application
		Request:
		{
			"email" : "demo22@gtree.com",
			"newPassword" : "1231",
			"confirmPassword": "1231"
		}
		Response:
		{
			"timestamp": "2021-10-06T10:51:43.771+00:00",
			"status": 400,
			"error": "Bad Request",
			"message": "No user found with the email",
			"path": "/pwd-mgmt/v1/reset"
		}

    4. A Valid Password reset request - Success scenario
		Request:
		{
			 "email" : "demo2@gtree.com",
			 "newPassword" : "131",
			 "confirmPassword": "131"
		}		
		Response:
		{
			"statusMessage": "Password reset Successful"
		}
	
How to stop API application:
	-  docker-compose down
   
How to check if a new 'Password Reset Event' is created:
	- Once the API is up and runnin, provide valid inputs to test password reset functionality, on successful reset of password a API will respond with an output as shown below.
	- Login to the link:  http://localhost:8161/admin/queues.jsp  (admin/admin userid/pwd on the prompt)
	  This will show increase of the messages/Events in the Queue:"PassWordUpdateQueue" on each successful password reset execution.

How to check if the database is updated with the new password: 
	- Log into the link:  http://localhost:8085/h2-console/login.do
	  Enter below details:
	  Execute query : SELECT * FROM USER_PWD  (to check updated password on the successful API POST call)
 