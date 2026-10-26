package com.marcos.estik.security;

import java.lang.reflect.Method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration 
@EnableMethodSecurity 
@RequiredArgsConstructor 
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/notifications",
                    "/notifications/**", 
                    "/error", 
                    "/v3/api-docs/**", 
                    "/swagger-ui/**", 
                    "/swagger-ui.html"
                ).permitAll()

                // users for role USER
                .requestMatchers(
                    "/users/{id}"
                ).hasRole("USER")

                // tickets for role USER
                .requestMatchers(
                    "/tickets",
                    "/tickets/{id}",
                    "/tickets/{id}/cancel",
                    "/tickets/{id}/histories",
                    "/tickets/{id}/reopen"
                ).hasRole("USER")

                // facilities for role USER
                .requestMatchers(
                    HttpMethod.GET,
                    "/facilities",
                    "/facilities/{id}"
                ).hasRole("USER")

                // departaments for role USER
                .requestMatchers(
                    HttpMethod.GET,
                    "/departaments",
                    "/departaments/{id}"
                ).hasRole("USER")

                .requestMatchers(
                    "/users"
                ).hasRole("ARCH")

                .requestMatchers(
                    "/tickets/**"
                ).hasRole("ARCH")

                .anyRequest().hasRole("IT")

            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean 
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role("IT").implies("ARCH")
            .role("ARCH").implies("USER")
            .build();
    }

}
