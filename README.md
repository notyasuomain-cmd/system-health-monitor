# System Health Monitor API

A simple REST API built with Java and Spring Boot for monitoring the status of software services.

I created this project to gain hands-on experience with Java backend development, REST APIs, automated testing, and the structure of a Spring Boot application.

## Features

- List all monitored systems
- Retrieve a system by ID
- Add a new system
- Update the status of an existing system
- Handle missing systems with `404 Not Found`
- Validate system statuses using a Java enum
- Unit and API integration tests

## Technologies

- Java 17
- Spring Boot
- Maven
- REST API
- JUnit 5
- MockMvc
- Git

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/systems` | Get all systems |
| `GET` | `/api/systems/{id}` | Get a system by ID |
| `POST` | `/api/systems` | Add a new system |
| `PUT` | `/api/systems/{id}/status` | Update a system's status |

## Example

### Get a system

```bash
curl http://localhost:8080/api/systems/2
```

Example response:

```json
{
  "id": 2,
  "name": "sensor-service",
  "status": "OFFLINE"
}
```

### Add a system

```bash
curl -X POST http://localhost:8080/api/systems \
  -H "Content-Type: application/json" \
  -d '{"id":4,"name":"radar-service","status":"ONLINE"}'
```

### Update system status

```bash
curl -X PUT http://localhost:8080/api/systems/2/status \
  -H "Content-Type: application/json" \
  -d '{"status":"MAINTENANCE"}'
```

Supported statuses:

- `ONLINE`
- `OFFLINE`
- `MAINTENANCE`

## Project Structure

The application separates HTTP handling from business logic:

```text
HTTP Request
     |
     v
SystemController
     |
     v
SystemService
     |
     v
SystemInfo
```

`SystemController` handles REST requests and HTTP responses, while `SystemService` contains the application logic and manages the monitored systems.

The project currently stores data in memory using a Java `ArrayList`, so data is reset when the application restarts.

## Running the Application

### Requirements

- Java 17

Clone the repository and run:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080/api/systems
```

## Testing

Run all tests with:

```bash
./mvnw test
```

The project includes:

- Unit tests for `SystemService`
- Integration tests for the REST API using Spring Boot and MockMvc
- Tests for successful requests
- `404 Not Found` handling
- `400 Bad Request` handling for invalid status values

## What I Learned

Through this project I practiced:

- Building a REST API with Spring Boot
- Java classes, constructors, getters, setters, lists, and enums
- Controller and service separation
- Dependency injection
- Converting between JSON and Java objects
- Working with HTTP methods and status codes
- Handling invalid requests
- Writing unit tests with JUnit
- Testing REST endpoints with MockMvc
- Using Maven and Git in a Java project

## Possible Future Improvements

- Generate system IDs on the backend
- Add persistent database storage
- Add request validation
- Improve API error responses
- Add more system health information and monitoring functionality
