###################################
# Stage 1: Build the application
###################################
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

###################################
# Stage 2: Run the Java application
###################################
FROM openjdk:17-jdk-slim AS runtime

# Copy the JAR file from the build stage
COPY --from=build /app/target/docker-multi-stage-0.0.1-SNAPSHOT.jar /docker-multi-stage.jar

# Expose port 8080 where the Java application will run
EXPOSE 8080

# Run the Java application
CMD ["java", "-jar", "/docker-multi-stage.jar"]

###################################
# Stage 3: Set up Nginx as a reverse proxy
###################################
FROM nginx:alpine AS proxy

# Expose port 80 for Nginx
EXPOSE 80

# Command to run Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]
