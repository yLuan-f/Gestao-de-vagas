# ---- Etapa de build ----
FROM maven:3-eclipse-temurin-26 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# ---- Etapa de runtime ----
FROM eclipse-temurin:26-jre
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]