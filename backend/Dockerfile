FROM lpicanco/java11-alpine
VOLUME 	/tmp
ARG JAR_FILE=/backend/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
