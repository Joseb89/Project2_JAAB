FROM openjdk:11
COPY target/*.jar doctor_api.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "doctor_api.jar"]