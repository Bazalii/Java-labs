FROM openjdk:17-jdk-alpine

ADD "/owners/application/target/*.jar" owners-application.jar

ENTRYPOINT [ "java", "-jar", "owners-application.jar" ]