# Start with a base image containing Java runtime (17) and Maven
FROM maven:3.8.4-openjdk-17 as build

COPY . /usr/src/app

WORKDIR /usr/src/app

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

COPY --from=build /usr/src/app/target/user-management-microservice-0.0.1-SNAPSHOT.jar /usr/app/user-management-microservice.jar

WORKDIR /usr/app

EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","user-management-microservice.jar"]
