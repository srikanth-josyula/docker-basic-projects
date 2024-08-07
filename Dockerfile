# Use the Use openjdk:17-jdk-slim: This is a smaller, more lightweight image. 
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/springboot-docker-volume-management-0.0.1.jar /app/springboot-docker-volume-management.jar

#This copies the application.properties file from your local path to /app/application.properties inside the container. This is useful if you want to specify the exact path and filename within the container.
COPY src/main/resources/application.properties /app/application.properties


#This copies the application.properties file from your local path to /app/ inside the container, preserving the original filename.
#COPY COPY src/main/resources/application.properties /app/application.properties /app/

# Define a volume for temporary files
VOLUME /tmp

# Set the default command to run the JAR file with the system property
ENTRYPOINT ["java", "-Dtemp.path=/tmp", "-jar", "/app/springboot-docker-volume-management.jar", "--spring.config.location=file:/app/application.properties"]

# Expose the port your application runs on
EXPOSE 6090