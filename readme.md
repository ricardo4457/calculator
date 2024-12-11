
# Calculator REST API with Gradle Configuration

## Overview
This project implements a simple REST API for basic arithmetic operations (addition, subtraction, multiplication, and division) with support for arbitrary precision signed decimal numbers. The project is modular, ensuring separation of concerns and maintainability.

## Functional Requirements
- REST API for arithmetic operations: sum, subtraction, multiplication, and division.
- Supports two operands (a and b).
- Handles arbitrary precision signed decimal numbers.

## Non-Functional Requirements
- Modular project with Gradle build system.
- Modules: `rest` and `calculator-logic`.
- Built on Spring Boot 3.4.0.
- Uses RabbitMQ and Spring AMQP for inter-module communication.
- Configuration via `application.properties`.
- No XML configuration except potentially for logging.

## Gradle Configuration Overview
The project uses Gradle for build and dependency management. It has a root module and two submodules: `calculator-logic` and `rest`.

### Root `build.gradle`
The root module contains shared configurations and dependencies:
```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version "\${springBootVersion}"
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
}

allprojects {
    group = 'com.example.calculator'
    version = '1.0.0'

    repositories {
        mavenCentral()
    }

    tasks.withType(JavaCompile) {
        options.encoding = 'UTF-8'
    }
}

subprojects {
    apply plugin: 'java'

    dependencies {
        testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
    }

    tasks.test {
        useJUnitPlatform()
    }
}
```

### Root `settings.gradle`
The root `settings.gradle` file includes submodules:
```groovy
rootProject.name = 'calculator'
include 'calculator-logic'
include 'rest'
```

### Shared Configuration via `gradle.properties`
This file provides global configuration properties for the project:
```properties
org.gradle.jvmargs=-Xmx1024m
springBootVersion=3.4.0
javaVersion=17
```

### Submodule: `calculator-logic`
This module implements the core arithmetic operations.

**`calculator-logic/build.gradle`**:
```gradle
dependencies {
    implementation 'org.springframework:spring-context:5.3.9'

    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
}
```

### Submodule: `rest`
This module exposes calculator functionality via REST endpoints.

**`rest/build.gradle`**:
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-amqp'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
}
```

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

## How to Run
1. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```
2. Run the application:
   ```bash
   java -jar build/libs/calculator.jar
   ```
3. Access the API at `http://localhost:8080/api`.

## Example API Usage
### Addition Example
**Request**:
```
GET /api/add?a=10&b=5
```
**Response**:
```
15
```

### Division Example
**Request**:
```
GET /api/divide?a=10&b=0
```
**Response**:
```
{
  "error": "Division by zero is not allowed."
}
```

## Class: `CalculatorLogicService`

**Package**: `com.example.calculator`

This class contains the core logic for arithmetic operations.

### Annotations
- `@Service`: Marks this class as a service component in the Spring framework.

### Methods
1. **`add(BigDecimal a, BigDecimal b)`**
   - **Description**: Adds two numbers.
   - **Parameters**:
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The sum of `a` and `b`.

2. **`subtract(BigDecimal a, BigDecimal b)`**
   - **Description**: Subtracts the second number from the first.
   - **Parameters**:
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The result of `a - b`.

3. **`multiply(BigDecimal a, BigDecimal b)`**
   - **Description**: Multiplies two numbers.
   - **Parameters**:
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The product of `a` and `b`.

4. **`divide(BigDecimal a, BigDecimal b)`**
   - **Description**: Divides the first number by the second.
   - **Parameters**:
     - `a`: Numerator.
     - `b`: Denominator.
   - **Throws**: `IllegalArgumentException` if `b` is zero.
   - **Returns**: The result of `a / b`.

---

## Class: `CalculatorController`

**Package**: `com.example.rest`

This class acts as the REST controller for exposing the calculator's operations via HTTP endpoints.

### Annotations
- `@RestController`: Indicates this is a REST controller.
- `@RequestMapping("/api")`: Specifies the base URL for all endpoints in this controller.

### Endpoints
1. **`GET /api/add`**
   - **Description**: Adds two numbers.
   - **Parameters** (via `@RequestParam`):
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The sum of `a` and `b`.

2. **`GET /api/subtract`**
   - **Description**: Subtracts one number from another.
   - **Parameters**:
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The result of `a - b`.

3. **`GET /api/multiply`**
   - **Description**: Multiplies two numbers.
   - **Parameters**:
     - `a`: First number.
     - `b`: Second number.
   - **Returns**: The product of `a` and `b`.

4. **`GET /api/divide`**
   - **Description**: Divides one number by another.
   - **Parameters**:
     - `a`: Numerator.
     - `b`: Denominator.
   - **Throws**: `IllegalArgumentException` if `b` is zero.
   - **Returns**: The result of `a / b`.

---
