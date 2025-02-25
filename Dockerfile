FROM eclipse-temurin:17-jdk as build
WORKDIR /app

# Copy build files first (for better layer caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create a non-root user to run the application
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set permissions
RUN chown -R appuser:appuser /app
USER appuser

# Expose the application port
EXPOSE 8080

# Set environment variables that can be overridden
ENV JWT_SECRET=default_jwt_secret_replace_me \
    JWT_EXPIRATION=900000 \
    SPRING_PROFILES_ACTIVE=prod

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
