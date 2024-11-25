FROM eclipse-temurin:17.0.13_11-jdk-jammy AS builder

WORKDIR /app

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY receipt-processor-service ./receipt-processor-service
RUN ./gradlew :receipt-processor-service:clean :receipt-processor-service:bootJar

FROM eclipse-temurin:17.0.13_11-jre-jammy AS final
WORKDIR /app
COPY --from=builder /app/receipt-processor-service/build/libs/receipt-processor-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]