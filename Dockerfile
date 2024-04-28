FROM openjdk:latest
COPY out/Task5.jar  appTask5.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appTask5.jar"]