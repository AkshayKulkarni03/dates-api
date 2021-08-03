FROM gcr.io/distroless/java:11

ARG JAR_FILE=target/dates-api-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
