System Design:

- VaultSystem is a backend aggregator tool
- Banks like Axis, ICICI, HDFC are real external banks, and they each have their own databases (which VaultSystems does't control).
- VaultSystem platform is aggregating all the bank's data that a particular user has — so it must have it;s own internal system to manage links.

Database Design for Vault System
- aggregator-db (Vault System's database)
        ├── customer (table)           <-- All the customer's data resides in this db who have registered to use VaultSystems
        ├── banks (table)              <-- available banks to link that VaultSystems front end shows
        ├── user_bank_mapping (table)  <-- user's linked banks mappings. Once user links all banks from the application, next time user should not be prompted again and again. The data should be loaded next time                                             he logs in to the app. So, we have to keep track of all the banks the user is affiliated to in the database.
- axis-db (external database)
        ├── real customer data of Axis bank
        ├── real accounts, transactions, loans, cards, branches the account is linked to
- icici-db (external database)
        ├── real customer data of ICICI bank
        ├── real accounts, transactions, loans, cards

- hdfc-db (external database)
        ├── real customer data of HDFC bank
        ├── real accounts, transactions, loans, cards

Dev Smoke Test Results

Microservices Registered in Netflix Eureka Server:

![image](https://github.com/user-attachments/assets/3bf1784b-347b-4e26-ab41-208dee066486)

API Gateway server: One way entry to all the microservices with retry Pattern added upon failures

![image](https://github.com/user-attachments/assets/ff130fe8-ae3c-44e1-be1d-d44ccb07eb62)

Scenario-1: Customer has created an account, cards, loans microservices are down

![image](https://github.com/user-attachments/assets/cb93f22f-fd95-41c0-a3e4-da8e8868f44c)

Scenario-2: Customer has account in the bank, has a home loan, and a credit card

![image](https://github.com/user-attachments/assets/530bb6fa-8dfc-400e-af86-58e7d0547a56)

Scenario-3: Customer has account, a home loadn and no card

![image](https://github.com/user-attachments/assets/4268b3af-dd01-488f-9c43-6ea2743057e3)

Integrated all microservices logging to Grafana

![image](https://github.com/user-attachments/assets/a11f394e-4095-4c7e-9934-300db8640b11)

Integrated all microservices to Prometheus - which states metrics and graphs of microservice health Example: Metrics via Prometheus: System CPU Usage Metrics

![image](https://github.com/user-attachments/assets/aa2f3b55-c0a0-4cfa-86d1-17666502c065)

Integration of microservices with Micrometer

![image](https://github.com/user-attachments/assets/98eb035f-68cd-410d-b6ad-54e4c8cca2d7)


Skills:

    Building Microservices:
      - Spring Boot - REST APIs - Spring Data JPA - Open API Documentation and Swagger - Generate Docker Images with DockerFile, Buildpacks, Google Jib  - 12 Factor and 15 Factor Methodologies
    Centrailzed Configuration Management for Spring Microservices
      - Spring Boot Profiles - Spring Cloud Config - Spring Cloud Bus - Spring Cloud Monitor - Liveness and Readiness Probes - MySQL
    Spring Microservices Registration and Discovery:
      - Service Discovery and Service Registration using Netflix Eureka Client
    Spring Cloud API Gateway:
      - Load Balancing - Spring Cloud Gateway - Cross Cutting Concerns, Tracing, Logging using Gateway server - Resiliency using Circuit Breaker Pattern - Implementing Retry Pattern in Gateway server
      - Implementing Redis Rate Limiter in Gateway server - Bulkhead Pattern
    Observability and Monitoring of Microservices:
       Logging using Grafana Loki and Promtail. - Metrics monitoring using Actuator, Micrometer, Prometheus and Grafana - Creating and sending Alert notifications using Grafana
      - Distributed Tracing using OpenTelemetry, Grafana and Tempo
    Microservices Security:
      - Oauth2 and OpenID - IAM and KeyCloak - Securing Gateway as a resource service - Authentication and Authorization Code Grant type inside Gateway server
    Event Driven Asynchronous Communication:
      - Pub Sub Model - RabbitMq - Spring Cloud Functions - Spring Cloud Streams - Async communication using Apache Kafka







