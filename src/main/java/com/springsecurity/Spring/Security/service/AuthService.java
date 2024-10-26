package com.springsecurity.Spring.Security.service;


import com.springsecurity.Spring.Security.dto.LoginDto;
import com.springsecurity.Spring.Security.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginDto inputUser){
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(inputUser.getEmail(),inputUser.getPassword())
       );
       UserEntity user = (UserEntity) authentication.getPrincipal();
        return jwtService.getJwtToken(user);
    }
}
