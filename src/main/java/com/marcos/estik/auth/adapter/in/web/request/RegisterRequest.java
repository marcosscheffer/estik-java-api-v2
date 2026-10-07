package com.marcos.estik.auth.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
    @Email 
    String email,
    @NotBlank 
    String username,
    @NotBlank
    String password,
    @NotNull
    UUID facility
) {
    
}
