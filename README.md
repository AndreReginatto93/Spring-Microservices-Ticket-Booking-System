# Event Booking Microservices System

This project is a microservices-based event booking system built with Java, Spring Boot, and Maven. It consists of four main microservices and an API Gateway for routing and resilience.

## Technologies

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Cloud Gateway**
- **Keycloak**
- **Docker**
- **Kafka**
- **MySQL**
- **MySQLWorkbench** (database management)
- **IntelliJ IDEA** (development IDE)
- **Postman** (API testing)

## Architecture

- **API Gateway**: Routes requests to the appropriate microservices, provides circuit breaking, and exposes API documentation endpoints.
- **Booking Service**: Handles event booking requests, validates users and inventory, and publishes booking events to Kafka.
- **Order Service**: Listens to booking events from Kafka, creates orders, and updates inventory.
- **Inventory Service**: Manages event and venue inventory, provides inventory data, and updates event capacities.

## Services Overview

### 1. API Gateway
- **Tech**: Spring Cloud Gateway (MVC)
- **Responsibilities**:
    - Routes HTTP requests to Booking and Inventory services.
    - Provides circuit breaker and fallback for Booking Service.
    - Exposes API documentation endpoints for downstream services.

### 2. Booking Service
- **Tech**: Spring Boot, Kafka
- **Responsibilities**:
    - Receives booking requests.
    - Validates user and inventory.
    - Publishes booking events to Kafka.

### 3. Order Service
- **Tech**: Spring Boot, Kafka
- **Responsibilities**:
    - Listens to booking events from Kafka.
    - Creates order records.
    - Calls Inventory Service to update event capacity.

### 4. Inventory Service
- **Tech**: Spring Boot, SQL Database
- **Responsibilities**:
    - Provides event and venue inventory data.
    - Updates event capacities.
    - Exposes endpoints for querying and updating inventory.

## Running the Project

1. **Start Kafka** (for event communication).
2. **Start Inventory Service** (`localhost:8080`).
3. **Start Booking Service** (`localhost:8081`).
4. **Start Order Service** (`localhost:8082`).
5. **Start API Gateway**  (`localhost:8090`).

## API Endpoints (via API Gateway)

- **Booking**: `POST /api/v1/booking` 
- **Inventory**:
    - `GET /api/v1/inventory/events`
    - `GET /api/v1/inventory/venue/{venueId}`
    - `GET /api/v1/inventory/event/{eventId}`
    - `PUT /api/v1/inventory/event/{eventId}/capacity/{capacity}`
- **API Docs**:
    - `/docs/bookingservice/v3/api-docs`
    - `/docs/inventoryservice/v3/api-docs`
