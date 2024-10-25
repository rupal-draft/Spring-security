package com.springsecurity.Spring.Security.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data

public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }

}
