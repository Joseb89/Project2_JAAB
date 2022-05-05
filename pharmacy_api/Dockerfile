FROM openjdk:11
COPY target/*.jar pharmacy_api.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "pharmacy_api.jar"]