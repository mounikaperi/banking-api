# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim
# Information around who maintains the image
MAINTAINER mounikaperi
# Add the application's jar to image
COPY target/ba-accounts-0.0.1-SNAPSHOT.jar ba-accounts-0.0.1-SNAPSHOT.jar
# execute the image
ENTRYPOINT ["java", "-jar", "ba-accounts-0.0.1-SNAPSHOT.jar"]
