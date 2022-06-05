FROM openjdk:17-jdk-alpine

WORKDIR /cats
ADD "./application/target/*.jar" cats-application.jar
ADD "./core/target/*.jar" cats-core.jar
ADD "./data-access-layer/target/*.jar" cats-data-access-layer.jar
ADD "./rabbit-mq-configuration/target/*.jar" cats-rabbit-mq-configuration.jar

ENTRYPOINT [ "java", "-jar", "cats-application.jar" ]