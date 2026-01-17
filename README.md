🏭 FRP Manufacturing Platform (Saga-based Microservices)
Overview

This project demonstrates a production-grade, event-driven microservices architecture using the Saga pattern to manage distributed transactions between services.

It models a real-world FRP tank manufacturing business, covering:

Order creation
Inventory reservation
Failure compensation
Event-driven consistency (no distributed transactions)

🧱 Architecture
┌──────────────┐        OrderCreated        ┌─────────────────┐
│ Order Service│ ─────────────────────────▶│ Inventory Service│
└──────────────┘                            └─────────────────┘
        ▲                                             │
        │                                             │
        │     MaterialReserved / Failed               │
        └─────────────────────────────────────────────┘

Order Service → Saga initiator
Inventory Service → Saga participant
Kafka → Event backbone
Eventual consistency via Saga compensation

🛠 Tech Stack
Java 17
Spring Boot 3
Spring Data JPA
Spring Kafka
H2 (local dev)
PostgreSQL (prod-ready)
Apache Kafka
Maven
Docker (Kafka & Postgres)

📦 Services
1️⃣ Order Service

Responsibilities
Create orders
Maintain order lifecycle
Publish OrderCreated
Compensate on inventory failure

Key APIs
POST /api/v1/orders
GET  /api/v1/orders/{id}
GET  /api/v1/orders?page=0&size=10

Order States
CREATED → MATERIAL_RESERVED → COMPLETED
CREATED → CANCELLED (on failure)

2️⃣ Inventory Service

Responsibilities
Consume OrderCreated
Reserve raw materials
Emit saga outcome events

Saga Outcomes
MaterialReserved
MaterialReservationFailed

🔁 Saga Flow
Happy Path

OrderCreated
→ Inventory reserves material
→ MaterialReserved
→ Order status updated

Failure Path (Compensation)

OrderCreated
→ Inventory insufficient
→ MaterialReservationFailed
→ Order cancelled

✔ No synchronous calls
✔ No distributed transactions
✔ Fully event-driven

🚀 Running Locally (No Kafka)
# Order Service
cd order-service
mvn spring-boot:run

# Inventory Service
cd inventory-service
mvn spring-boot:run

Local profile uses:
H2 database
No-op Kafka publishers/consumers

🚀 Running With Kafka
