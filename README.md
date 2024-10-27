# Spring Security JWT Authentication with OAuth2 and Role-Based Authorization

This project demonstrates a comprehensive implementation of authentication and authorization mechanisms using JWT, OAuth2, and Spring Security’s role-based and granular permissions.

## Overview

The project covers the following key security concepts:
- **Spring Security Basics**: Core components, security filters, and configurations.
- **Web Security Configuration**: Configuring `SecurityFilterChain` to secure specific endpoints.
- **JWT (JSON Web Token)**: Creating and verifying JWTs for user authentication.
- **OAuth2 Authentication**: Configuring Google OAuth2 for secure user authentication.
- **Refresh Token and Access Token**: Handling token expiration and reauthentication.
- **Role-Based Authorization**: Assigning roles to users to restrict access to specific endpoints.
- **Granular Authorization**: Using permissions and authorities for fine-grained control over access.
- **Security Method Annotations**:
  - **@Secured**
  - **@PreAuthorize**
- **Common Security Attacks**:
  - **CSRF (Cross-Site Request Forgery)**
  - **XSS (Cross-Site Scripting)**
  - **SQL Injection**
- **Spring Security Exception Handling**: Custom handling for unauthorized access and other security exceptions.

## Features

- **User Signup and Login**: Users can register and obtain a JWT access token upon login.
- **JWT Refresh and Access Token**: Supports refresh tokens for re-authentication without needing to log in again.
- **OAuth2 Authentication**: Google OAuth2 configuration for third-party login.
- **Role-Based and Authority-Based Authorization**: Controls access using roles and specific permissions.
- **Security Configurations**: Configuring filters, entry points, and authentication methods in Spring Security.

## Project Structure

- **JWT Creation and Verification**: Handles token generation during login and verification on each request.
- **Authentication and Authorization**: Ensures only authenticated users can access certain endpoints.
- **Exception Handling**: Manages security exceptions like unauthorized access and bad credentials.

## Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/rupal-draft/Spring-security.git
    ```
2. **Configure Properties**: Add your `jwt.secretKey` in `application.properties` or `application.yml`. 

3. **Set up OAuth2 Credentials**: Add Google OAuth2 client ID and secret in `application.yml` as follows:

    ```yaml
    spring:
      security:
        oauth2:
          client:
            registration:
              google:
                client-id: YOUR_CLIENT_ID
                client-secret: YOUR_CLIENT_SECRET
    ```

4. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## API Documentation

You can access the API documentation via Postman for details on endpoints, request bodies, and response types:
- [Postman Documentation Link](https://documenter.getpostman.com/view/30415721/2sAY4sj4zC)

## Security Endpoints

The application includes basic Spring Actuator endpoints for health checks:
- Health Check: `http://localhost:8080/actuator/health`
- API Documentation: `http://localhost:8080/swagger-ui/index.html`

## Example Usage

1. **Signup**: Register a new user.
2. **Login**: Authenticate with your credentials to receive a JWT access token and refresh token.
3. **OAuth2 Login**: Authenticate using Google OAuth2.
4. **Access Protected Resources**: Use the JWT access token in headers to access secure endpoints.
5. **Token Refresh**: Use the refresh token endpoint to obtain a new access token upon expiration.

## Technologies Used

- **Spring Boot**
- **Spring Security**
- **OAuth2 (Google Authentication)**
- **JWT (JSON Web Tokens)**
- **Maven**

## Security Annotations and Permissions

The project uses Spring Security annotations for role-based and authority-based access:
- **@Secured**: Used to restrict access based on roles.
- **@PreAuthorize**: Allows for fine-grained authorization based on specific permissions and authorities.

## Learning Outcomes

- Developed a secure application using JWT for authentication and authorization.
- Implemented Google OAuth2 for third-party authentication.
- Gained proficiency in handling refresh tokens and access tokens.
- Implemented role-based and granular permission-based authorization using authorities and roles.
- Gained experience in using `@Secured` and `@PreAuthorize` annotations for method-level security.
