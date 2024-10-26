package com.springsecurity.Spring.Security.controller;

import com.springsecurity.Spring.Security.dto.AuthDto;
import com.springsecurity.Spring.Security.dto.LoginDto;
import com.springsecurity.Spring.Security.dto.UserDto;
import com.springsecurity.Spring.Security.service.AuthService;
import com.springsecurity.Spring.Security.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthDto inputUser){
        UserDto userDto = userService.signup(inputUser);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<String> login(@RequestBody LoginDto inputUser, HttpServletResponse response){
        String token = authService.login(inputUser);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
