FROM eclipse-temurin:17.0.13_11-jdk-jammy AS builder

WORKDIR /app

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY eureka-server ./eureka-server
RUN ./gradlew :eureka-server:clean :eureka-server:bootJar

FROM eclipse-temurin:17.0.13_11-jre-jammy AS final
WORKDIR /app
COPY --from=builder /app/eureka-server/build/libs/eureka-server.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]