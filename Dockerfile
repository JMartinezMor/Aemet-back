FROM maven:3.9.3-jdk-18-slim AS MAVEN_BUILD
COPY . /build
WORKDIR /build
MAINTAINER aemet-back
COPY --from=MAVEN_BUILD /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]