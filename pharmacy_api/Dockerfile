#
# Build stage
#
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml .
RUN mvn -f /home/app/pom.xml clean -DskipTests package

#
# Package stage
#
FROM openjdk:11-jre-slim
ARG SOURCE_JAR=/home/app/target/*.jar
ARG DEST_JAR=/usr/local/lib/pharmacy_api.jar
COPY --from=build ${SOURCE_JAR} ${DEST_JAR}
EXPOSE 8084
ENTRYPOINT ["java","-jar","/usr/local/lib/pharmacy_api.jar"]