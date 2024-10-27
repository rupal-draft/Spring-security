package com.springsecurity.Spring.Security.dto;

import com.springsecurity.Spring.Security.entities.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class AuthDto {
    private String email;
    private String password;
    private String name;
    private Set<Roles> roles;
}
