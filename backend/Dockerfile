FROM lpicanco/java11-alpine
VOLUME 	/volumes/dapalza_mysql/_data
ARG JAR_FILE=/backend/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
