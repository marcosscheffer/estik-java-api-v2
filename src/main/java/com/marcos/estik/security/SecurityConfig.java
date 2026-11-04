package com.marcos.estik.security;

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

import jakarta.servlet.DispatcherType;
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
                .dispatcherTypeMatchers(DispatcherType.ASYNC, DispatcherType.ERROR).permitAll()

                .requestMatchers(
                    "/auth/**",
                    "/error", 
                    "/v3/api-docs/**", 
                    "/ws/**",
                    "/swagger-ui/**", 
                    "/swagger-ui.html",
                    "/files/**"
                ).permitAll()

                .requestMatchers(
                    "/notifications/**"

                ).hasRole("USER")

                .requestMatchers(
                    HttpMethod.GET,
                    "/computers/**",
                    
                    "/histories/**"
                ).hasRole("USER")

                // stock for role USER
                .requestMatchers(
                    HttpMethod.GET,
                    "/stock/balances/**"
                ).hasRole("USER")
                
                .requestMatchers(
                    HttpMethod.GET,
                    "/stock/movements/**"
                ).hasRole("USER")

                // users for role USER
                .requestMatchers(
                    "/users/{id}"
                ).hasRole("USER")

                .requestMatchers(
                    HttpMethod.GET,
                    "/users"
                ).hasRole("REGULATOR")

                // tickets for role USER
                .requestMatchers(
                    "/tickets",
                    "/tickets/{id}",
                    "/tickets/{id}/cancel",
                    "/tickets/{id}/histories",
                    "/tickets/{id}/reopen",
                    "/tickets/{id}/messages",
                    "/tickets/{id}/messages/stream"
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
                    "/tickets/{id}/forward"
                ).hasRole("REGULATOR")

                .requestMatchers(
                    "/tickets/**"
                ).hasRole("TECHNICIAN")

                .requestMatchers(
                    "/stock/"
                ).hasRole("TECHNICIAN")

                .requestMatchers(
                    "/items/**"
                ).hasRole("TECHNICIAN")

                .requestMatchers(
                    "/computers/**"
                ).hasRole("TECHNICIAN")

                .requestMatchers(
                    "/categories/**"
                ).hasRole("TECHNICIAN")

                .anyRequest().hasRole("ADMINISTRATOR")

            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean 
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role("ADMINISTRATOR").implies("TECHNICIAN")
            .role("TECHNICIAN").implies("REGULATOR")
            .role("REGULATOR").implies("USER")
            .build();
    }
}
