FROM openjdk:11
COPY target/*.jar patient_api.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "patient_api.jar"]