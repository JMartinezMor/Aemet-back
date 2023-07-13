FROM openjdk:18
MAINTAINER aemet-back
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]