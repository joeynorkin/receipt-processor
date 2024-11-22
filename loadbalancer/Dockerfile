FROM gradle:8.3-jdk17 AS build

ARG SERVICE_NAME=loadbalancer

WORKDIR /app

COPY gradlew settings.gradle build.gradle /app/
COPY gradle /app/gradle
COPY ${SERVICE_NAME}/ /app/${SERVICE_NAME}

WORKDIR /app/${SERVICE_NAME}
RUN ./gradlew clean bootJar

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/${SERVICE_NAME}/build/libs/${SERVICE_NAME}.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# FROM gradle:8.3-jdk17 AS build
#
# WORKDIR /app
#
# COPY gradlew settings.gradle build.gradle /app/
# COPY gradle /app/gradle
# COPY loadbalancer/ /app/loadbalancer
#
# WORKDIR /app/loadbalancer
# RUN ./gradlew clean bootJar
#
# # Stage 2: Create the runtime image
# FROM openjdk:17-jdk-slim
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the JAR from the build stage
# COPY --from=build /app/loadbalancer/build/libs/loadbalancer.jar app.jar
#
# # Expose the application port
# EXPOSE 8080
#
# # Run the application
# ENTRYPOINT ["java", "-jar", "app.jar"]