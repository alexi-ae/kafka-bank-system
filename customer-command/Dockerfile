# Etapa 1: Construcción
#FROM maven:3.9.4-eclipse-temurin-17 AS build
FROM maven:latest AS build
WORKDIR /app

# Copiar solo el POM del proyecto padre y el módulo customer-command
COPY ../pom.xml /app/pom.xml
COPY ../customer-command /app/customer-command

# Construir el módulo customer-command
WORKDIR /app/customer-command
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
#FROM eclipse-temurin:17-jdk
FROM openjdk:17-slim
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/customer-command/target/*.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8081

# Iniciar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
