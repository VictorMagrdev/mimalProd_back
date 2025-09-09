FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copiamos solo gradlew y configuración para poder usar el wrapper
COPY gradlew gradlew.bat build.gradle settings.gradle /app/
COPY gradle /app/gradle

# Damos permisos
RUN chmod +x gradlew

# Default: correr la app con Gradle en modo dev
CMD ["./gradlew", "bootRun"]
