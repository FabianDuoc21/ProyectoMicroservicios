FROM eclipse-temurin:21-jdk-alpine
COPY target/ms-pago-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]