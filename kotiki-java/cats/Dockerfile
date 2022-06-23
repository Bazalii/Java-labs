FROM openjdk:17-jdk-alpine

ADD "/cats/application/target/*.jar" cats-application.jar

ENTRYPOINT [ "java", "-jar", "cats-application.jar" ]