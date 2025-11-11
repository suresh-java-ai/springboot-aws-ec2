#Use the official OpenJDK 21 image from Docker Hub
#FROM eclipse-temurin:17-jdk
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
# Set working directory inside the container
WORKDIR /app
# Copy the complied Java application JAR file into the container
COPY target/*.jar app.jar
# Expose the port the spring boot application will run on
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]