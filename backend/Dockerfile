FROM openjdk:11
CMD ["./gradlew", "clean", "package"]
# VOLUME 	/tmp

# COPY gradlew .
# COPY gradle gradle
# COPY build.gradle .
# COPY settings.gradle .
# COPY src src
# RUN chmod +x ./gradlew
# RUN ./gradlew bootJar

# ARG JAR_FILE=build/libs/*.jar
# COPY ${JAR_FILE} app.jar

ADD build/libs/app.jar app.jar
ENV PROFILE prod

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]
