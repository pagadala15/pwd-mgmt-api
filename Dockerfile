FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
COPY --from=build /usr/src/app/target/pwd-mgmt-api.jar /usr/app/api.jar 
EXPOSE 8085
ENTRYPOINT ["java","-jar","/usr/app/api.jar"]