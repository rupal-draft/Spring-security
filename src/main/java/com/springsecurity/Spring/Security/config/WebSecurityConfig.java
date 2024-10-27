package com.springsecurity.Spring.Security.config;


import com.springsecurity.Spring.Security.entities.enums.Permissions;
import com.springsecurity.Spring.Security.filter.JwtAuthFilter;
import com.springsecurity.Spring.Security.handler.Oauth2Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.springsecurity.Spring.Security.entities.enums.Permissions.FLAT_DELETE;
import static com.springsecurity.Spring.Security.entities.enums.Permissions.FLAT_UPDATE;
import static com.springsecurity.Spring.Security.entities.enums.Roles.ADMIN;
import static com.springsecurity.Spring.Security.entities.enums.Roles.BUYER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private static final String[] publicRoutes = {
            "/error", "/user/**", "/home.html"
    };
    private final JwtAuthFilter jwtAuthFilter;
    private final Oauth2Handler oauth2Handler;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(HttpMethod.GET,"/flats")
                        .hasAnyRole(ADMIN.name(),BUYER.name())
                        .requestMatchers(HttpMethod.PUT,"/flats")
                        .hasAuthority(FLAT_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,"/flats")
                        .hasAuthority(FLAT_DELETE.name())
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer
                                .failureUrl("/login?error=true")
                                .successHandler(oauth2Handler)
                );


        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
