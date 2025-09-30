
# 🧠 Quiz Application Microservices

A **scalable and modular Quiz Application** built using **Spring Boot** and a **Microservices Architecture**.  
It leverages **Service Discovery (Eureka)** and **API Gateway (Spring Cloud Gateway)** for robust, dynamic service communication and a unified entry point.

---

## 📑 Table of Contents

- [Project Overview](#1-project-overview)
- [Microservices Architecture](#2-microservices-architecture)
- [Technology Stack](#3-technology-stack)
- [Getting Started](#4-getting-started)
  - [Prerequisites](#🔧-prerequisites)
  - [Setup and Running](#▶️-setup-and-running)
- [API Documentation](#5-api-documentation)
  - [API Gateway Endpoints](#🔹-api-gateway-endpoints)
  - [Question Service API](#🔹-question-service-api)
  - [Quiz Service API](#🔹-quiz-service-api)
- [Microservices Concepts & Design](#6-microservices-concepts--design)
  - [Service Registry (Eureka)](#📌-service-registry-eureka)
  - [API Gateway (Spring Cloud Gateway)](#📌-api-gateway-spring-cloud-gateway)
  - [Load Balancing & Inter-Service Communication](#📌-load-balancing--inter-service-communication)
  - [Scalability and Horizontal Scaling](#📌-scalability-and-horizontal-scaling)
- [Project Structure](#7-project-structure)
- [ Contribution](#-contribution)

---

## 1. Project Overview

The Quiz Application is broken down into **independent microservices** to improve maintainability, scalability, and deployment flexibility.  

**Core functionalities include:**
- Managing a bank of questions
- Creating and managing quizzes using those questions

### Microservices Table

| Microservice       | Description                                           | Port       |
|--------------------|-------------------------------------------------------|------------|
| **service-registry** | The Eureka Server for service registration/discovery | `8761`     |
| **api-gateway**      | The single entry point for all client requests       | `8745`     |
| **question-service** | Manages all CRUD operations for the question bank    | Auto-assigned |
| **quiz-service**     | Manages quiz creation, fetching questions, scoring   | Auto-assigned |

---

## 2. Microservices Architecture

- Clients make requests **only to the API Gateway**
- API Gateway routes to the appropriate microservice (e.g., **quiz-service**)
- All microservices **register with Eureka**
- Services communicate internally using **Feign Clients** and logical service names

**Example:**  
`quiz-service` fetches questions from `question-service` via **Eureka**.

---

## 3. Technology Stack

| Category           | Technology Used                         |
|--------------------|------------------------------------------|
| **Language**       | Java                                    |
| **Frameworks**     | Spring Boot, Spring Cloud               |
| **Service Discovery** | Spring Cloud Netflix Eureka           |
| **API Gateway**    | Spring Cloud Gateway                    |
| **Communication**  | OpenFeign Client                        |
| **Build Tool**     | Maven                                   |

---

## 4. Getting Started

### 🔧 Prerequisites
- JDK **17+**
- Maven **3.6+**
- IDE (IntelliJ IDEA, Eclipse, VS Code)

---

### ▶️ Setup and Running

1. **Clone the repository:**
   ```bash
   git clone https://github.com/kr254na/Quiz-Application-Backend---Microservices.git
   cd quiz-application

2. **Start Service Registry:**
   ```bash
   cd service-registry
   mvn spring-boot:run
   ```
   Runs on port 8761

3. **Start Question Service:**
   ```bash
   cd question-service
   mvn spring-boot:run


4. **Start Quiz Service:**
   ```bash
   cd quiz-service
   mvn spring-boot:run


5. **Start API Gateway:**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```
   Runs on port 8745

## 5. API Documentation

All external API calls must go through the API Gateway 
```http
http://localhost:8745
```

### 🔹 API Gateway Endpoints
|    Prefix    | Target Microservice |
|--------------|---------------------|
| /question/** |   question-service  |  
|   /quiz/**	 |     quiz-service    |

### 🔹 Question Service API

#### Base Path (via Gateway):
```http
http://localhost:8745/question
```

#### Method	Endpoint	Description
Retrieve all questions
```http
GET	/allQuestions
```
Retrieves questions by category/domain
```http
GET	/category/{domain}	
```
Add a new question
```http
POST	/add (RequestBody: Question)
```
Update an existing question
```http
PUT	/update	
```
Delete a question by ID
```http
DELETE	/delete/{id}
```
Generate list of questions for quiz
```http
GET	/generate
```
Retrieve questions by IDs
```http
POST	/getQuestions	
```
Calculate final score for quiz
```http
POST	/getScore
```

### 🔹 Quiz Service API

#### Base Path (via Gateway)
```http
http://localhost:8745/quiz
```

#### Method Endpoint	Description
Creates a quiz
( fetches questions from question-service )
```http
POST	/create
```	
Get Quiz By Quiz Id
```http
GET	/get/{id}
```
Submit quiz answers and calculates score
```http
POST	/submit/{id}	
```

## 6. Microservices Concepts & Design
### 📌 Service Registry (Eureka)

**Service Name:** service-registry

**Port:** 8761

Central hub for service discovery

### 📌 API Gateway (Spring Cloud Gateway)

**Service Name:** api-gateway

**Port:** 8745

Single entry point for all client requests

Routes to services dynamically via Eureka

### 📌 Load Balancing & Inter-Service Communication

Feign Client used for communication

Load balancing automatically handled by Spring Cloud

### 📌 Scalability and Horizontal Scaling

Each service runs independently

Can deploy multiple instances of high-demand services

Eureka + Gateway + Feign enable dynamic scaling

## 7. Project Structure
```bash
/Quiz-Application-Root
├── api-gateway
│   └── src/main/java/com/krishna/api_gateway/
├── service-registry
│   └── src/main/java/com/krishna/service_registry/
├── question-service
│   └── src/main/java/com/krishna/question_service/
└── quiz-service
    └── src/main/java/com/krishna/quiz_service/
```
# ⭐ Contribution

Feel free to fork this repo, raise issues, and submit pull requests!
