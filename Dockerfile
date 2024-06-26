##############################
# BUILD						 #
##############################
FROM maven:3.9.7-eclipse-temurin-22-alpine as build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

##############################
# PRODUCTION				 #
##############################
FROM openjdk:22-rc as production

WORKDIR /app

COPY --from=build /app/target/hotel-reservation-system.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
