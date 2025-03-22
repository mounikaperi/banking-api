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





