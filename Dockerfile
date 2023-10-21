FROM adoptopenjdk/openjdk11:alpine-jre
LABEL authors="Vladislav Petrov"
WORKDIR /opr/app
COPY target/emc-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]