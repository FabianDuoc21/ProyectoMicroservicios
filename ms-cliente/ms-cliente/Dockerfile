FROM eclipse-temurin:21-jdk-alpine
COPY target/ms-cliente-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]