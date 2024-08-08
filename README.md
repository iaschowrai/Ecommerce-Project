
# Ecommerce Microservice Project

## Overview

This project involves the development of a comprehensive eCommerce microservices architecture. It includes the following services:

1. **Product Service**: Manages the creation and viewing of products, serving as the product catalog.
2. **Order Service**: Handles the ordering of products.
3. **Inventory Service**: Checks the stock availability of products.
4. **Notification Service**: Sends notifications to users after an order is placed. This service is stateless and does not use a database.

## Service Interactions

- **Order Service**, **Inventory Service**, and **Notification Service** interact with each other.
- The system supports both synchronous and asynchronous communication between services.

## Technology Stack

- **Product Service**: Interacts with MongoDB.
- **Order Service**: Uses MySQL for database interactions.
- **Inventory Service**: Also uses MySQL.
- **Notification Service**: Stateless, does not use a database.

## API Gateway

An API Gateway is used to route requests to the appropriate services. It acts as a gatekeeper, handling requests and interactions with the various microservices, without exposing internal hostnames or IP addresses.

## Security

The application is secured using **Keycloak**, which serves as the authorization server.

## Observability

To ensure comprehensive monitoring and observability, the following tools are utilized:

- **Grafana**: For visualizing metrics and performance data.
- **Grafana Loki**: Aggregates logs from microservices.
- **Grafana Tempo**: Provides distributed tracing to track request flows.
- **Prometheus**: Collects and stores metrics data.

## Containerization and Orchestration

All services are containerized using **Docker**. Docker Compose is used to manage multi-container applications. The Docker Compose setup will eventually be migrated to **Kubernetes** for advanced orchestration and scaling.

---
