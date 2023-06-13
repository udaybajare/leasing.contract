#Start Application

###Run MySql container
Change directory to ./docker and run following command to run mysql container
* docker-compose up

###Running Application
Once the db is up, execute following command to run the application
* gradle bootRun

Once the application is up the API details can be found at following link :
http://localhost:8080/swagger-ui/index.html
 
###NOTE
This application requires following :
* Java 11
* Docker   