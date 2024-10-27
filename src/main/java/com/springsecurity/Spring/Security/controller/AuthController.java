package com.springsecurity.Spring.Security.controller;

import com.springsecurity.Spring.Security.dto.*;
import com.springsecurity.Spring.Security.service.AuthService;
import com.springsecurity.Spring.Security.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    @Value("${deploy.env}")
    private String deployment;
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthDto inputUser){
        UserDto userDto = userService.signup(inputUser);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto inputUser, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(inputUser);
        Cookie cookie = new Cookie("refreshToken",loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployment));
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest request){
        String refreshToken = Arrays
                .stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("No refresh token found!!"));
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
