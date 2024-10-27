package com.springsecurity.Spring.Security.service;


import com.springsecurity.Spring.Security.dto.LoginDto;
import com.springsecurity.Spring.Security.dto.LoginResponseDto;
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
    private final UserService userService;

    public LoginResponseDto login(LoginDto inputUser){
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(inputUser.getEmail(),inputUser.getPassword())
       );
       UserEntity user = (UserEntity) authentication.getPrincipal();
       String accessToken = jwtService.getAccessJwtToken(user);
       String refreshToken = jwtService.getRefreshJwtToken(user);
       return new LoginResponseDto(accessToken,refreshToken,user.getId());
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long id = jwtService.getUserId(refreshToken);
        UserEntity user = userService.getUserFromId(id);
        String newAccessToken = jwtService.getAccessJwtToken(user);

        return new LoginResponseDto(newAccessToken,refreshToken, user.getId());
    }
}
