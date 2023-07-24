package com.example.sofiyaproductservice.config;

import com.example.sofiyaproductservice.filter.JwtTokenFilter;
import com.example.sofiyaproductservice.service.AuthenticationService;
import com.example.sofiyaproductservice.service.JwtService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((authorizer) -> {
                    authorizer
                            .requestMatchers("/api/v1/product/add",
                                    "/api/v1/product//get-all",
                                    "/api/v1/product/search",
                                    "/api/v1/product/{productId}/update",
                                    "/api/v1/product/{productId}/delete")
                            .authenticated();
                })
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore((Filter) new JwtTokenFilter(authenticationService,jwtService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
