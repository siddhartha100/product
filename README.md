Product application is an application that can do the following operations
1. Fetch product information(from an external api and from a NoSQL database) and display it to a client
2. Create/Replace product information to a nosql database.


A. Technologies used:
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


B. BaseURL: http://127.0.0.1:32000/v1


C. Endpoints:



1. Find product information based on product id
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




2. Create/Replace product information to a database
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


D. Running and testing the application 
1. Clone the application from github
   - git clone https://github.com/siddhartha100/product.git
2. This application was tested in Java 11. Install Java 11 if needed.
3. Update application-dev.properties property file with correct Mongo DB url, port, database name
   - I created a local Mongo DB docker instance and created a "products" database from command line. You need to have Docker installed in your computer for this.   
     - docker run -d --name mongodb -p 27888:27017 -e MONGO_INITDB_ROOT_USERNAME=mongo_user_name_here -e MONGO_INITDB_ROOT_PASSWORD=mongo_password_here mongo
	 - Based on the port you are using during Mongo container creation, update the same port number in application property file(27888 in my case)
	 - We shall use mongo db username and pasword as a vm arguments when starting the application. In production, we have to take them from a vault.
4. We can run the application from command line or we can debug the application in an IDE like Intellij
   - If we want to test the application in an IDE, we have to setup application run/debug config in the IDE and add the follwoing vm arguments to run the app
      -Dspring.data.mongodb.username={mongo_user_name_here} -Dspring.data.mongodb.password={mongo_password_here} -Dspring.profiles.active=dev
   - If we want to run the application from command line, we have to build the application jar file and then run it from command line
      - Go inside application project directory and run -> mvn clean install (For creating the application jar file. Maven must be on class path)
	  - Go inside the target directory which is inside the project directory through command line
	  - Run -> java -jar -Dspring.profiles.active=dev -Dspring.data.mongodb.username=myusername -Dspring.data.mongodb.password=myPassWord1! product-0.0.1-SNAPSHOT.jar (For starting the app using dev profile)
	  - Application will start if all the previous configurations are correct

5. Forward the application metrics to promethus and build Grafana dashboard
   - I have used local Prometheus Docker container to get the application metrics. In a production environment, this is usually a managed service where we have to forward application metrics.
   - Prometheus configuration file prometheus.yml is kept in src/main/resources folder and configured for local testing.
   - Run command -> docker run -p 9090:9090 -v /path/to/prometheus.yml prom/prometheus to run a local prometheus container. Dashboard is available at http://127.0.0.1:9090
   - Run command -> docker run -d --name=grafana -p 3000:3000 graphana/graphana to run a local Graphana container. Dashboard is available at http://127.0.0.1:3000
   - Login to Grafana using admin,admin default credentials. Go to configuration and add Prometheus as data source. Provive http url settings as http://127.0.0.1:9090 in the datasource form and save. 
   - Now we can create different dashboards in Grafana using Promethus metrics. 
	 	 
