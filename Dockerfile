# Use the openjdk:17-jdk-slim: This is a smaller, more lightweight image.
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/springboot-docker-volume-management-0.0.1.jar /app/springboot-docker-volume-management.jar

# Copy the application.properties file into the container
COPY src/main/resources/application.properties /app/application.properties

# Define volumes for logs and temporary files (Created using the docker volume create command and mounted when running the container. This volume persists data outside of the container’s lifecycle)
VOLUME /logs

# Define volumes temporary files from Dockerfile (This volume is created automatically by Docker when you run the container, but it’s local to the container unless explicitly mounted to a host directory.)
VOLUME /temp

# Set the default command to run the JAR file with system properties
ENTRYPOINT ["java", "-Dlog.path=/logs", "-Dtemp.path=/temp", "-jar", "/app/springboot-docker-volume-management.jar", "--spring.config.location=file:/app/application.properties"]

# Expose the port your application runs on
EXPOSE 6090
