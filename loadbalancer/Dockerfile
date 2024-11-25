FROM eclipse-temurin:17.0.13_11-jdk-jammy AS builder

WORKDIR /app

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY loadbalancer ./loadbalancer
RUN ./gradlew :loadbalancer:clean :loadbalancer:bootJar

FROM eclipse-temurin:17.0.13_11-jre-jammy AS final
WORKDIR /app
COPY --from=builder /app/loadbalancer/build/libs/loadbalancer.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]