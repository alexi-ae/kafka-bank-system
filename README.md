# BANKING SYSTEM

Sistema de Transferencias de Dinero en Tiempo Real

## Tabla de Contenidos

1. [Antecedente](#Antecedente)
2. [Problema](#Problema)
3. [Objetivo](#Objetivo)
4. [Solución](#Solución)
5. [Tecnologías Utilizadas](#tecnologíasutilizadas)
6. [Autor](#autor)

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

Servicios Auxiliares:

Service Registry (Eureka):
* Función: Registro y descubrimiento de microservicios.
* Beneficio: Permite que los microservicios se descubran dinámicamente, eliminando configuraciones manuales de direcciones IP o puertos.
* Escenario de Uso: Cuando un nuevo microservicio se inicia, se registra automáticamente en Eureka, lo que permite que otros servicios lo localicen fácilmente.

API Gateway:
* Función:
    * Actúa como un único punto de entrada para los clientes.
    * Realiza tareas como enrutamiento, balanceo de carga, autenticación y limitación de tasa (rate limiting).
* Beneficio: Simplifica las interacciones del cliente, abstrae la complejidad de los microservicios subyacentes y mejora la seguridad.
* Escenario de Uso: Un cliente envía una solicitud al Gateway, que enruta la petición al microservicio correspondiente (por ejemplo, Transaction Service para transferencias).

Config Server:
* Función: Proporciona una fuente centralizada de configuración para todos los microservicios.
* Beneficio:
    * Permite cambios de configuración sin necesidad de redeploy.
    * Mantiene consistencia y versionado de configuraciones.
    * Escenario de Uso: Un cambio en las propiedades del Transaction Service se actualiza automáticamente desde el Config Server.

Kafka:
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
