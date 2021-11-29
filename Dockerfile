FROM openjdk:8-jdk-alpine

RUN mkdir /var/play

ARG DEPENDENCY=build
ADD ${DEPENDENCY}/libs/play-spring-boot.jar /var/play/play.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/var/play/play.jar"]
