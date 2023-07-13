FROM openjdk:18
MAINTAINER aemet-back
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]