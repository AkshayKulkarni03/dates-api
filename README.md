# dates-api

Dates operation API with [Spring Boot](http://projects.spring.io/spring-boot/).

## Requirements
For building and running the application you need:

- [JDK 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `nl.abnamro.polar.assessment.dates.api.DatesApiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### Swagger definition
Once application is running with above mentioned steps, API documentation can be accessed via [Swagger UI](http://localhost:8080/swagger-ui.html).

### Lombok Annotations
This project packages the annotations of [Project Lombok](https://projectlombok.org/) as separate JAR to improve compiling and handling Java sources generated by [Delombok](https://projectlombok.org/features/delombok).

### AKS cluster
Application is getting deployed on Azure AKS cluster with [Github actions](https://github.com/AkshayKulkarni03/dates-api/tree/main/.github/workflows) and deployment details are in [manifest](https://github.com/AkshayKulkarni03/dates-api/tree/main/manifests) folder