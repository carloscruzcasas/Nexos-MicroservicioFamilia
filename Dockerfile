FROM openjdk:8-jdk-slim
COPY "./target/microservicio-familia.jar" "app.jar"
EXPOSE 8080
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]