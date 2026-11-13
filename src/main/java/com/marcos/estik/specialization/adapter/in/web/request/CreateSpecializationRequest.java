package com.marcos.estik.specialization.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSpecializationRequest(
    @NotBlank 
    String name
) {
    
}
