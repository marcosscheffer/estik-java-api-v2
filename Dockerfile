# syntax=docker/dockerfile:1

FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /workspace

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw -B -q  dependency:go-offline

COPY src src

RUN ./mvnw -B -q clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine AS runtime

WORKDIR /app

RUN addgroup -S estik && adduser -S -G estik -H estik

COPY --from=build /workspace/target/estik-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /app/uploads/chat && chown -R estik:estik /app

USER estik

EXPOSE 8080

ENTRYPOINT [ "java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar" ]