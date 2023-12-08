FROM openjdk:17
LABEL authors="xenedium"
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]