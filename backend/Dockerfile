# springboot build
FROM openjdk:11-jdk
EXPOSE 8082

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENV PROFILE prod

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]