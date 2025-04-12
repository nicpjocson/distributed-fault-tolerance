FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/ViewGradesNode-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
