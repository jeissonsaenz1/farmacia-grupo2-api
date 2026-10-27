
# Etapa 1: Compilación de la aplicación
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copiar primero la configuración de Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Dar permisos y descargar dependencias
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline -B

# Copiar el código fuente y generar el JAR
COPY src/ ./src/
RUN ./mvnw clean package -DskipTests -B

# Etapa 2: Imagen de ejecución
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Ejecutar la aplicación con un usuario sin privilegios
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

USER appuser

# Puerto configurable para Render
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dserver.port=${PORT:-8080} -jar app.jar"]
