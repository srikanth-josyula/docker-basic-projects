# Use the Use openjdk:17-jdk-slim: This is a smaller, more lightweight image. 
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the build context (local filesystem) into the root directory of the container
COPY target/springboot-swapi-service-0.0.1-SNAPSHOT.jar /springboot-swapi-service.jar

# Define the default command to run when the container starts, This command will execute the JAR file using Java
ENTRYPOINT ["java", "-jar", "/springboot-swapi-service.jar"]

# Expose the application port
EXPOSE 8080