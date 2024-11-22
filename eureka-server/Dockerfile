FROM gradle:8.3-jdk17 AS build

WORKDIR /app

COPY gradlew settings.gradle build.gradle /app/
COPY gradle /app/gradle
COPY eureka-server/ /app/eureka-server

WORKDIR /app/eureka-server
RUN ./gradlew clean bootJar

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/eureka-server/build/libs/eureka-server.jar app.jar

# Expose the application port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]