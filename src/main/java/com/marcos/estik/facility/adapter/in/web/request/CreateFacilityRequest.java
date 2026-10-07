package com.marcos.estik.facility.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateFacilityRequest(
    @NotBlank 
    String name,
    @NotBlank 
    String code
) {
    
}
