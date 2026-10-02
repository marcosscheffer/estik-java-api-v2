package com.marcos.estik.auth.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    @NotBlank 
    String username,
    @NotBlank
    String password
) {
    
}
