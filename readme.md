# Calculator REST API

## Overview
This project is a simple REST API that performs basic arithmetic operations (addition, subtraction, multiplication, and division) with support for arbitrary precision signed decimal numbers. The project is divided into two modules: `calculator-logic` and `rest`.

## Functional Requirements
- REST API that exposes the sum, subtraction, multiplication, and division operations.
- Support for 2 operands only (a and b).
- Support for arbitrary precision signed decimal numbers.

## Non-Functional Requirements
- Project uses Gradle with at least 2 modules: `rest` and `calculator-logic`.
- Uses Spring Boot 3.4.0 as the foundation for both modules.
- Uses RabbitMQ and Spring AMQP for inter-module communication.
- Configuration via `application.properties` (default of Spring Boot).
- No XML configuration (except, potentially, for logging).
- Use of Git for version control.

## Project Structure
```
calculator
│
├── calculator-logic
│   ├── src/main/java/calculator-logic
│   │   └── CalculatorLogicService.java
│   ├── src/main/resources
│   │   └── application.properties
│   └── build.gradle
│
├── rest
│   ├── src/main/java/rest
│   │   └── CalculatorController.java
│   ├── src/main/resources
│   │   └── application.properties
│   └── build.gradle
│
├── src/main/resources
│   └── application.properties
│
├── build.gradle
└── settings.gradle
```

## Getting Started

### Prerequisites
- Java 21
- Gradle
- RabbitMQ

### Installation
1. **Clone the repository:**
    ```sh
    git clone https://github.com/ricardo4457/calculator
    cd your-repo-name
    ```

2. **Ensure RabbitMQ is running:**
    - Download and install RabbitMQ .
    - Start the RabbitMQ server.

3. **Build the project:**
    ```sh
    ./gradlew build
    ```

4. **Run the modules:**
    - Start the `calculator-logic` module:
        ```sh
        cd calculator-logic
        ./gradlew bootRun
        ```
    - In a new terminal window, start the `rest` module:
        ```sh
        cd ../rest
        ./gradlew bootRun
        ```

### API Endpoints
- **Addition:**
    ```sh
    curl "http://localhost:8080/api/add?a=10&b=5"
    ```
- **Subtraction:**
    ```sh
    curl "http://localhost:8080/api/subtract?a=10&b=5"
    ```
- **Multiplication:**
    ```sh
    curl "http://localhost:8080/api/multiply?a=10&b=5"
    ```
- **Division:**
    ```sh
    curl "http://localhost:8080/api/divide?a=10&b=5"
    ```