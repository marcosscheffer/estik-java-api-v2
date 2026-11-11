package com.marcos.estik.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                    "/swagger-ui/**", 
                    "/swagger-ui.html",
                    "/files/**"
                ).permitAll()

                .requestMatchers(
                    "/chats/**",
                     "/notifications/**"
                ).authenticated()

                // endpoint users permissions

                .requestMatchers(
                    "/users/me"
                ).authenticated()

                .requestMatchers(
                    HttpMethod.PUT,
                    "/users/**"
                ).authenticated()

                .requestMatchers(
                    HttpMethod.GET,
                    "/users"
                ).hasAnyAuthority("USER_MANAGE", "TICKET_MANAGE", "TICKET_SUPERVISOR")

                .requestMatchers(
                    "/users/**"
                ).hasAnyAuthority("USER_MANAGE")

                // endpoint tickets permissions

                .requestMatchers(
                    HttpMethod.GET,
                    "/tickets/**"
                ).authenticated()

                .requestMatchers(
                    HttpMethod.POST,
                    "/tickets",
                    "/tickets/{id}/reopen",
                    "/tickets/{id}/cancel"
                ).hasAuthority("TICKET_OPEN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/tickets/**"
                ).hasAuthority("TICKET_OPEN")

                .requestMatchers(
                    "/tickets/{id}/resolve",
                    "/tickets/{id}/assume"
                ).hasAuthority("TICKET_WORK")

                .requestMatchers(
                    "/tickets/**"
                ).hasAnyAuthority("TICKET_SUPERVISOR", "TICKET_MANAGE")

                // endpoint facilties & departaments permissions

                .requestMatchers(
                    "/facilities/**",
                    "/departaments/**"
                ).hasAnyAuthority("FACILITY_MANAGE")

                // endpoint stock permissions

                .requestMatchers(
                    HttpMethod.GET,
                    "/items/**",
                    "/stock/**",
                    "/categories/**"
                ).hasAuthority("STOCK_READ")

                .requestMatchers(
                    "/items/**",
                    "/stock/**",
                    "/categories/**"
                ).hasAuthority("STOCK_MANAGE")

                // endpoint computers permissions

                .requestMatchers(
                    HttpMethod.GET,
                    "/computers/**",
                    "/histories/**"
                ).hasAuthority("COMPUTER_READ")

                .requestMatchers(
                    "/computers/**",
                    "/histories/**"
                ).hasAuthority("COMPUTER_MANAGE")


                // endpoint access permissins

                .requestMatchers(
                    "/access/**"
                ).hasAuthority("SYSTEM_MANAGE")

                .requestMatchers(
                    "/specializations"
                ).hasAuthority("SYSTEM_MANAGE")

                .anyRequest().denyAll()
                
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
