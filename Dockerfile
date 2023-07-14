FROM openjdk:18
MAINTAINER aemet-back
COPY target/aemet-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "/app.jar"]