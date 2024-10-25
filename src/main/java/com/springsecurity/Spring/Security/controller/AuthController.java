package com.springsecurity.Spring.Security.controller;

import com.springsecurity.Spring.Security.dto.AuthDto;
import com.springsecurity.Spring.Security.dto.UserDto;
import com.springsecurity.Spring.Security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthDto inputUser){
        UserDto userDto = userService.signup(inputUser);
        return ResponseEntity.ok(userDto);
    }
}
