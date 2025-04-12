FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/UploadGradesNode-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "app.jar"]
