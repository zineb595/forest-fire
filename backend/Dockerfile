# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the pre-built jar file from your local target folder into the container
# Replace the file path with your actual .jar file location if it's different
COPY target/FireSimulation-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 9090

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
