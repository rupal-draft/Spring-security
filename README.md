# Spring Security JWT Authentication

This project demonstrates a basic implementation of JWT-based authentication in a Spring Boot application, designed with best practices in Spring Security.

## Overview

This project covers the following key concepts:
- **Spring Security Basics**: Core components, security filters, and configurations.
- **Web Security Configuration**: Configuring `SecurityFilterChain` to secure specific endpoints.
- **JWT (JSON Web Token)**: Creating and verifying JWTs for user authentication.
- **Common Security Attacks**:
  - **CSRF (Cross-Site Request Forgery)**
  - **XSS (Cross-Site Scripting)**
  - **SQL Injection**
- **Spring Security Exception Handling**: Custom handling for unauthorized access and other security exceptions.

## Features

- **User Signup and Login**: Users can register and obtain a JWT token upon login.
- **JWT Authentication**: The JWT is used to authenticate requests, allowing access to protected resources.
- **Security Configurations**: Configuration of filters, entry points, and authentication methods in Spring Security.

## Project Structure

- **JWT Creation and Verification**: Handles token generation during login and verification on each request.
- **Authentication and Authorization**: Ensures only authenticated users can access certain endpoints.
- **Exception Handling**: Manages security exceptions like unauthorized access and bad credentials.

## Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/rupal-draft/Spring-security.git
    ```
2. **Configure Properties**: Add your `jwt.secretKey` in `application.properties`.
3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## API Documentation

You can access the API documentation via Postman for details on endpoints, request bodies, and response types.
- [Postman Documentation Link](https://documenter.getpostman.com/view/30415721/2sAY4sj4zC)

## Security Endpoints

The application includes basic Spring Actuator endpoints for health checks:
- Health Check: `http://localhost:8080/actuator/health`
- API Documentation: `http://localhost:8080/swagger-ui/index.html`

## Example Usage

1. **Signup**: Register a new user.
2. **Login**: Authenticate with your credentials to receive a JWT token.
3. **Access Protected Resources**: Use the JWT token in headers to access secure endpoints.

## Technologies Used

- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Maven**

## Learning Outcomes

- Gained a solid understanding of Spring Security fundamentals and its internal mechanisms.
- Learned how to configure security settings to defend against CSRF, XSS, and SQL injection.
- Implemented JWT token handling for secure authentication and authorization.


