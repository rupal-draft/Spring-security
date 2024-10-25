package com.springsecurity.Spring.Security.dto;


import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
