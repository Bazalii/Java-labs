FROM openjdk:17-jdk-alpine

WORKDIR /owners
ADD "./application/target/*.jar" owners-application.jar

ENTRYPOINT [ "java", "-jar", "owners-application.jar" ]