# BANKING SYSTEM

Sistema de Transferencias de Dinero en Tiempo Real

## Tabla de Contenidos

1. [Antecedente](#Antecedente)
2. [Problema](#Problema)
3. [Objetivo](#Objetivo)
4. [Solución](#Solución)
5. [Deploy](#Deploy)
6. [Tecnologías_Utilizadas](#Tecnologías_Utilizadas)
6. [Autor](#Autor)

## Antecedente
En el sector financiero, la digitalización de procesos es fundamental para garantizar una experiencia de usuario eficiente, segura y confiable. Actualmente, las entidades requieren soluciones tecnológicas que no solo permitan la gestión de usuarios y cuentas, sino que también cumplan con estrictos estándares de seguridad, escalabilidad y facilidad de uso.

## Problema
La entidad carece de un sistema para:

- Registrar usuarios de manera eficiente.
- Validar datos de forma precisa y ágil para la aprobación de cuentas.
- Proveer acceso seguro a las cuentas asignadas a cada usuario.
- Facilitar transferencias seguras entre cuentas internas.

## Objetivo
Diseñar e implementar un sistema integral que permita:

1. Registrar nuevos usuarios de forma sencilla.
2. Validar y aprobar sus datos cumpliendo normativas de KYC (Know Your Customer).
3. Gestionar cuentas asignadas.
4. Realizar transferencias de dinero entre cuentas con rapidez y seguridad.

## Solución

### Descripción
La solución está diseñada como un ecosistema basado en microservicios, proporcionando modularidad, escalabilidad y facilidad de mantenimiento. Cada funcionalidad principal está encapsulada en un servicio independiente, comunicándose entre sí de manera eficiente mediante Kafka para la orquestación de eventos.

### Arquitectura
Cada microservicio sigue el enfoque de Arquitectura Hexagonal (también conocida como Arquitectura de Puertos y Adaptadores). Este diseño asegura que la lógica de negocio sea el núcleo de cada servicio y esté desacoplada de los detalles de implementación externa.

Microservicios Principales:

Auth Service:

* Funcionalidad: Gestión de autenticación y autorización.
* Implementación:
    - Uso de Spring Security y JWT para autenticación segura.
    - Permisos granulares según roles de usuario.

Customer Service:

* Funcionalidad: Manejo del registro de usuarios y validación de datos.
* Implementación:
    - Verificación de datos personales y cumplimiento de políticas KYC.
    - Almacenamiento seguro de información del cliente en una base de datos relacional.

Account Service:

* Funcionalidad: Gestión de cuentas bancarias.
* Implementación:
    - Creación y asignación de cuentas a clientes aprobados.
    - Exposición de APIs para consultar saldos y movimientos.

Transaction Service:

* Funcionalidad: Manejo de transferencias y auditoría de transacciones.
* Implementación:
    - Flujo de transferencias con confirmación por 2FA.
    - Registro detallado para auditorías y conciliaciones.    

Servicios Auxiliares: Ir a [kafka-bank-system-core](https://github.com/alexi-ae/kafka-bank-system-core)

Service Registry (Eureka): http://localhost:8761/
* Función: Registro y descubrimiento de microservicios.
* Beneficio: Permite que los microservicios se descubran dinámicamente, eliminando configuraciones manuales de direcciones IP o puertos.
* Escenario de Uso: Cuando un nuevo microservicio se inicia, se registra automáticamente en Eureka, lo que permite que otros servicios lo localicen fácilmente.

API Gateway: http://localhost:8099
* Función:
    * Actúa como un único punto de entrada para los clientes.
    * Realiza tareas como enrutamiento, balanceo de carga, autenticación y limitación de tasa (rate limiting).
* Beneficio: Simplifica las interacciones del cliente, abstrae la complejidad de los microservicios subyacentes y mejora la seguridad.
* Escenario de Uso: Un cliente envía una solicitud al Gateway, que enruta la petición al microservicio correspondiente (por ejemplo, Transaction Service para transferencias).

Config Server: http://localhost:9000
* Función: Proporciona una fuente centralizada de configuración para todos los microservicios.
* Beneficio:
    * Permite cambios de configuración sin necesidad de redeploy.
    * Mantiene consistencia y versionado de configuraciones.
    * Escenario de Uso: Un cambio en las propiedades del Transaction Service se actualiza automáticamente desde el Config Server.

Kafka: http://localhost:9092 - zookeeper: http://localhost:2181 
* Función: Sistema de mensajería para coordinar eventos entre microservicios.
* Beneficio: Maneja procesos asincrónicos, asegurando consistencia eventual y permitiendo escalabilidad.

---
Infraestructura Tecnológica:
- Docker: Contenerización de cada microservicio para facilitar el despliegue y la escalabilidad.
- Kafka:
    * Orquestación de eventos entre microservicios (por ejemplo, al registrar un usuario se crea automáticamente una cuenta asociada).
    * Garantiza consistencia eventual en sistemas distribuidos.
- Base de Datos: 
    * PostgreSQL para almacenar datos transaccionales.
    * MongoDB para almacenar datos de sessión y no transaccionales
    * Redis para el caché de datos, manejo de sessiones y tareas temporales.  
- Seguridad:
    * Cifrado de datos sensibles.
    * Gestión de roles y permisos a través de JWT.


![Solución-Arquitectura](./Arquitectura-Spring-Cloud.svg)

---

# Deploy
## Requisitos previos
Antes de realizar el despliegue de kafka-bank-system:
- Instalar docker
- Desplegar [kafka-bank-system-core](https://github.com/alexi-ae/kafka-bank-system-core)
    - config-server
    - registry (Eureka)
    - api-gateway

## Pasos de Despliegue

### 1. Crear la carpeta de trabajo
Primero, crea una carpeta llamada `bank-system` en tu máquina y navega dentro de ella:

```bash
mkdir bank-system
cd bank-system
```

### 2. Clona este repositorio tu máquina local:
Clonaremos el proyecto en nuestra carpeta de trabajo local
```bash
git clone https://github.com/alexi-ae/kafka-bank-system.git
```

### 3. Usar Docker para el despliegue (docker-compose)
Luego, en el proyecto clonado, ejecutaremos el docker-compose que contiene la configuración para el despliegue:
```bash
docker-compose up --build
```

### 4. Validación del Despliegue
Una vez que hayas ejecutado `docker-compose up --build`, puedes seguir estos pasos para validar que todo se ha levantado correctamente.

### 1. Verificar el estado de los contenedores

Primero, asegúrate de que todos los contenedores estén funcionando correctamente. Puedes usar el siguiente comando para ver el estado de los contenedores:
```bash
docker-compose ps
```
Esto debería mostrar algo como:
```bash
CONTAINER ID   IMAGE                                   COMMAND                  CREATED              STATUS                   PORTS                                                  NAMES
6b6fa4638195   kafka-bank-system-transaction-command   "java -jar app.jar"      About a minute ago   Up About a minute        0.0.0.0:8083->8083/tcp                                 transaction-command
455ce05d0ddc   kafka-bank-system-customer-command      "java -jar app.jar"      About a minute ago   Up About a minute        0.0.0.0:8081->8081/tcp                                 customer-command
c930e8916091   kafka-bank-system-account-command       "java -jar app.jar"      About a minute ago   Up About a minute        0.0.0.0:8082->8082/tcp                                 account-command
3062773e39e5   kafka-bank-system-auth-command          "java -jar app.jar"      About a minute ago   Up About a minute        0.0.0.0:8080->8080/tcp                                 auth-command
2eac7965d4a9   redis:latest                            "docker-entrypoint.s…"   About a minute ago   Up About a minute        0.0.0.0:6379->6379/tcp                                 redis
9a0e94b77d15   mongo:latest                            "docker-entrypoint.s…"   About a minute ago   Up About a minute        0.0.0.0:27017->27017/tcp                               authdb
5927461d9173   confluentinc/cp-kafka:latest            "/etc/confluent/dock…"   About a minute ago   Up About a minute        0.0.0.0:9092->9092/tcp                                 kafka
d7f834b38ec1   postgres:15                             "docker-entrypoint.s…"   About a minute ago   Up About a minute        0.0.0.0:5433->5432/tcp                                 customer-db
8b84a6e05f5c   postgres:15                             "docker-entrypoint.s…"   About a minute ago   Up About a minute        0.0.0.0:5434->5432/tcp                                 account-db
3fa469983de7   postgres:15                             "docker-entrypoint.s…"   About a minute ago   Up About a minute        0.0.0.0:5435->5432/tcp                                 transaction-db
98d952d96f10   zookeeper:3.7                           "/docker-entrypoint.…"   About a minute ago   Up About a minute        2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   zookeeper
16bd58dc5924   kafka-bank-system-core-api-gateway      "sh -c 'sleep 20 && …"   8 minutes ago        Up 7 minutes             0.0.0.0:8099->8099/tcp, 8761/tcp                       api-gateway
bd462865dc6c   kafka-bank-system-core-registry         "sh -c 'sleep 20 && …"   8 minutes ago        Up 7 minutes (healthy)   0.0.0.0:8761->8761/tcp                                 registry
8f2c9f0d8b70   kafka-bank-system-core-config-server    "java -jar /app.jar"     8 minutes ago        Up 8 minutes (healthy)   0.0.0.0:9000->9000/tcp                                 config-server
```
Asegúrate de que el estado de todos los contenedores sea Up. Si algún contenedor no está "Up", revisa los logs para detectar posibles errores.

### 2.Verificar la conectividad de las APIs
Abre tu navegador o usa curl para hacer una petición GET a la ruta del API Gateway:

bash
Copiar código

```bash
curl http://localhost:8099/actuator/health
```
Si todo esta bien, tendrias que tener una respuesta como:
```bash
{"status":"UP"}
```
---

# Tecnologías_Utilizadas

### Lenguaje de Programación
- Java

### Frameworks y Bibliotecas
- Spring Boot
- Spring Security
- Spring Data JPA

### Bases de Datos
- PostgreSQL
- MongoDB
- Redis

### Mensajería
- Apache Kafka

### Herramientas de Contenerización y Despliegue
- Docker
- Docker Compose

### Gestión de Configuración y Comunicación
- Spring Cloud Config
- Eureka
- Spring Cloud Gateway

### Control de Versiones
- Git
- GitHub
---

## Autor
- **Nombre**: Alexi Acuña
- **Rol**: Desarrollador Principal
- **Descripción**: Desarrollador de software con experiencia en aplicaciones Java y Spring Boot.
  Apasionado por la creación de soluciones eficientes y escalables.
- **GitHub**: [github.com/alexi-ae](https://github.com/alexi-ae)
- **LinkedIn**: [linkedin.com/in/ronald-alexi-ae](https://www.linkedin.com/in/ronald-alexi-ae/)