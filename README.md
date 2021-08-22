A. Product application is an application that can do the following operations
1. Fetch product information and display that to the client
2. Update product information to a nosql database.


B. Technologies used:
1. Java 11
2. Spring Boot 2
3. Spring Boot Data Mongo DB
4. Spring Boot Web
5. Spring Boot Test
6. Spring boot Validation
7. Spring Boot Actuator
8. Spring Boot AOP
9. Micrometer
10. Lombok


C. BaseURL: http://127.0.0.1:32000/v1


D. Endpoints:



1. Find product information based in product id
- Url - {baseUrl}/product/{product-id}
- Http Method - GET
- Sample Response -
   {
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price": {
   "value": 13.34,
   "currency_code": "USD"
   }
   }
- Http response code - 200 (if item found)
- Http response code - 404 (if item not found)




2. Update product information to database
- Url - {baseUrl}/products/{product-id}
- Http Method - PUT
- Sample Request  - {
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price": {
   "value": 13.34,
   "currency_code": "USD"
   }
   }
- Sample Response - {
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price": {
   "value": 13.34,
   "currency_code": "USD"
   }
   }
- Http response code - 201 (item updated/created in database)
- Http response code - 400 (bad request)


E. Running and testing the application 
1. Clone the application from github
   - git clone https://github.com/siddhartha100/product.git
2. This application was tested in Java 11. Install Java 11 if needed.
3. Update application-dev.properties property file with correct Mongo DB url, port, database name
   - I created a local Mongo DB docker instance and created a "products" database from command line. You need to have Docker installed in your computer.   
     - docker run -d --name mongodb -p 27888:27017 -e MONGO_INITDB_ROOT_USERNAME=mongo_user_name_here -e MONGO_INITDB_ROOT_PASSWORD=mongo_password_here mongo
	 - based on the port you are using during in Mongo container creation, update the same in application property file(27888 in my case)
	 - we shall use username and pasword as a vm arguments when starting the app. In production, we have to take them from vault.
4. We can run the application from command line or we can open the application in an IDE like Intellij
   - If we want to test the application in an IDE, we have to setup application run/debug config in IDE and add the follwoing vm arguments to run the app
      -Dspring.data.mongodb.username={mongo_user_name_here} -Dspring.data.mongodb.password={mongo_password_here} -Dspring.profiles.active=dev
   - If we want to run the application from command line, we have to build the application jar file and then run it from command line
      - Go inside application project directory and run -> mvn clean install (For creating the application jar file. Maven must be on class path)
	  - Go inside the target directory inside the project directory through command line
	  - Run -> java -jar -Dspring.profiles.active=dev -Dspring.data.mongodb.username=myusername -Dspring.data.mongodb.password=myPassWord1! product-0.0.1-SNAPSHOT.jar (For starting dev profile)
	  - Application will start if all the previous configurations are correct

5. Forward the application logs in promethus and build Grafana dashboard
   - I have used local Prometheus Docker container to get the application logs. In a production environment, this is usually a managed service where we have to forward application logs
   - Prometheus configuration file prometheus.yml is kept in src/main/resources folder and configured for local testing.
   - Run command -> docker run 9090:9090 -v /path/to/prometheus.yml prom/prometheus to run a local prometheus container. Dashboard is available at http://127.0.0.1:9090
   - Run command -> docker run -d --name=grafana -p 3000:3000 graphana/graphana to run a local Graphana container. Dashboard is available at http://127.0.0.1:3000
   - Login to Grafana using admin,admin default credentials. Go to configuration and add Prometheus as data source. Provive http url settings as http://127.0.0.1:9090 in the form and save. 
     Now we can create different dashboards with Promethus metrics. 