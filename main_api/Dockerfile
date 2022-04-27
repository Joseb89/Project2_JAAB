FROM openjdk:11
COPY target/*.jar main_api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "main_api.jar"]