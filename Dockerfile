FROM openjdk:20-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "/app.jar"]
