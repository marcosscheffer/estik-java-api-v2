# syntax=docker/dockerfile:1

FROM eclipse-temurin:25-jdk AS build

WORKDIR /workspace

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw -B -q  dependency:go-offline

COPY src src

RUN ./mvwn -B -q clean package -DskipTests

FROM eclipse-temurin:25-jre AS runtinem

RUN groupadd --system estik && useradd --system --gid  estik --no-create-home estik

COPY --from=build /workspace/target/estik-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /app/uploads/chat && chown -R estik:estik /app

USER estik

EXPOSE 8080

ENTRYPOINT [ "java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar" ]