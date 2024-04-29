FROM openjdk:17-jdk-alpine
COPY target/Task5-1.0-SNAPSHOT.jar  appTask5.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appTask5.jar"]