FROM openjdk:18
MAINTAINER aemet-back
COPY target/aemet-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]