# Student–College Microservices

A Spring Boot Microservices project demonstrating independent services, service discovery, inter-service communication, and centralized API routing.

## 🚀 Project Overview

This project consists of four independent Spring Boot applications:

1. **Eureka Server** – Service discovery and registration.
2. **API Gateway** – Single entry point for client requests.
3. **Student Service** – Manages student information.
4. **College Service** – Manages college information.

Student Service communicates with College Service using **Spring Cloud OpenFeign** to retrieve college details associated with a student.

## 🏗️ Architecture

```text
                 Client / Postman
                        |
                        v
                 API Gateway
                   Port 3434
                        |
              +---------+---------+
              |                   |
              v                   v
        Student Service     College Service
           Port 8081           Port 8082
              |                   |
              v                   v
          student_db          college_db
              |
              | OpenFeign
              |
              +------> College Service

                 Eureka Server
                   Port 8761
```

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- Spring Web
- Spring Data JPA
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Cloud Gateway MVC
- Hibernate
- MySQL
- Maven
- IntelliJ IDEA
- Postman
- Git & GitHub

## 📁 Project Structure

```text
Student-College-Microservices/
│
├── eureka-server/
│
├── api-gateway/
│
├── student-service/
│
├── college-service/
│
├── .gitignore
│
└── README.md
```

## ⚙️ Application Configuration

| Application | Port | Database |
|---|---:|---|
| Eureka Server | 8761 | None |
| API Gateway | 3434 | None |
| Student Service | 8081 | student_db |
| College Service | 8082 | college_db |

### Database Setup

Create two MySQL databases:

```sql
CREATE DATABASE student_db;

CREATE DATABASE college_db;
```

Configure the database credentials using environment variables.

Example Student Service configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

For College Service, use `college_db` in the datasource URL.

Set the `DB_PASSWORD` environment variable before starting the services.

## ▶️ How to Run the Project

Start the applications in the following order:

**1. Eureka Server**

Run `EurekaServerApplication`.

Dashboard:
http://localhost:8761

**2. College Service**

Run `CollegeServiceApplication`.

Port: 8082

**3. Student Service**

Run `StudentServiceApplication`.

Port: 8081

**4. API Gateway**

Run `ApiGatewayApplication`.

Port: 3434

Check the Eureka dashboard to verify service registration.

## 🔗 REST API Endpoints

### College Service

Base URL: `http://localhost:8082/colleges`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/colleges` | Add a college |
| GET | `/colleges` | Get all colleges |
| GET | `/colleges/{id}` | Get college by ID |
| PUT | `/colleges/{id}` | Update college |
| DELETE | `/colleges/{id}` | Delete college |

### Student Service

Base URL: `http://localhost:8081/students`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students` | Add a student |
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get student with college details |
| PUT | `/students/{id}` | Update student |
| DELETE | `/students/{id}` | Delete student |

### API Gateway

Base URL: `http://localhost:3434`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/students/1` | Get student with college details |
| GET | `/colleges/1` | Get college details |

The Gateway forwards requests to the appropriate service through Eureka service discovery.

## 🔄 Inter-Service Communication

Student Service uses Spring Cloud OpenFeign to communicate with College Service.

When a client requests:

```http
GET /students/1
```

The application:

1. Fetches student information from Student Service.
2. Retrieves the student's `collegeId`.
3. Calls College Service through OpenFeign.
4. Retrieves the corresponding college information.
5. Combines both responses into a single JSON response.

### Example Response

```json
{
  "studentId": 1,
  "studentName": "Awais",
  "email": "awais@gmail.com",
  "course": "Computer Science",
  "college": {
    "collegeId": 1,
    "collegeName": "Pune Institute of Computer Technology",
    "address": "Pune, Maharashtra 411043",
    "university": "Savitribai Phule Pune University",
    "contactNumber": "+91-20-24371101"
  }
}
```

## ⭐ Key Features

- Independent microservices architecture.
- Service registration and discovery using Eureka.
- API routing using Spring Cloud Gateway.
- Inter-service communication using OpenFeign.
- Independent MySQL databases.
- RESTful CRUD operations.
- DTO-based response handling.
- Spring Data JPA and Hibernate integration.

## 🎯 Learning Objectives

This project demonstrates practical implementation of:

- Microservices Architecture
- Service Discovery
- API Gateway Pattern
- Inter-Service Communication
- REST API Development
- Database Integration
- Spring Boot Application Configuration

## 👨‍💻 Author

**Awais Shakil Bagawan**

GitHub: [awaisbagwan78-tech](https://github.com/awaisbagwan78-tech)

---

⭐ If you find this project useful, feel free to explore the repository and provide feedback.
