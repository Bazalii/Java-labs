FROM openjdk:17-jdk-alpine

WORKDIR /user-interface
ADD "./web/target/*.jar" users-application.jar
#ADD "core/target/users-core-1.0-SNAPSHOT.jar" users-core.jar
#ADD "data-access-layer/target/users-data-access-layer-1.0-SNAPSHOT.jar" users-data-access-layer.jar
#ADD "rabbit-mq-configuration/target/users-rabbit-mq-configuration-1.0-SNAPSHOT.jar" users-rabbit-mq-configuration.jar

#ENV JAVA_OPTS=""

ENTRYPOINT [ "java", "-jar", "users-application.jar" ]