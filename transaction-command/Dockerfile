# Etapa 1: Construcción
#FROM maven:3.9.4-eclipse-temurin-17 AS build
FROM maven:latest AS build
WORKDIR /app

# Copiar solo el POM del proyecto padre y el módulo transaction-command
COPY ../pom.xml /app/pom.xml
COPY ../transaction-command /app/transaction-command

# Construir el módulo transaction-command
WORKDIR /app/transaction-command
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
#FROM eclipse-temurin:17-jdk
FROM openjdk:17-slim
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/transaction-command/target/*.jar app.jar

# Establecer el perfil activo (prod) al ejecutar el contenedor
ENV SPRING_PROFILES_ACTIVE=prod

# Exponer el puerto del microservicio
EXPOSE 8083

# Iniciar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
