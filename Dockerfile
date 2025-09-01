# Etapa 1: Construir el artefacto JAR con Gradle
FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /app

# Copiar los archivos de Gradle y descargar dependencias
COPY build.gradle settings.gradle gradlew gradlew.bat /app/
COPY gradle /app/gradle
RUN ./gradlew dependencies

# Copiar el resto del código fuente y construir la aplicación
COPY src /app/src
RUN ./gradlew build -x test

# Etapa 2: Crear la imagen final con solo el JRE y el JAR
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar el JAR construido desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-Djwt.secret=ac9c8a7b2dc5c9bdc3a7fed6888d3c3f", "-jar", "app.jar"]
