FROM openjdk:17-jdk-alpine

ADD "/user-interface/web/target/*.jar" users-application.jar

ENTRYPOINT [ "java", "-jar", "users-application.jar" ]