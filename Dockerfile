# Build stage
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw ./
# Pre-download dependencies (optional, failure resilient)
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline -B || true
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
