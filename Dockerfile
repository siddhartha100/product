FROM openjdk:11
COPY target/product-0.0.1-SNAPSHOT.jar /usr/src/app/application.jar
WORKDIR /usr/src/app
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Dspring.data.mongodb.username=myusername","-Dspring.data.mongodb.password=myPassWord1!","application.jar"]
