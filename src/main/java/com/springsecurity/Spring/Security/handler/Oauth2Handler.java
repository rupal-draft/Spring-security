package com.springsecurity.Spring.Security.handler;

import com.springsecurity.Spring.Security.entities.UserEntity;
import com.springsecurity.Spring.Security.service.JwtService;
import com.springsecurity.Spring.Security.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2Handler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final JwtService jwtService;
    @Value("${deploy.env}")
    private String deployment;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken =(OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        UserEntity user = userService.findByEmail(email);
        if(user == null){
            String name = oAuth2User.getAttribute("name");
            UserEntity toBeSavedUser = UserEntity
                    .builder()
                    .email(email)
                    .name(name)
                    .build();
            user=userService.saveUser(toBeSavedUser);
        }
        String accessToken = jwtService.getAccessJwtToken(user);
        String refreshToken = jwtService.getRefreshJwtToken(user);
        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployment));
        response.addCookie(cookie);
        String frontEndUrl = "http://localhost:8080/home.html?token="+accessToken;
        response.sendRedirect(frontEndUrl);
    }
}
