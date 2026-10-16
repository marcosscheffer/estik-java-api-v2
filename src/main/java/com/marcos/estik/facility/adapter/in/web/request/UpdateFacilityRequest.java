package com.marcos.estik.facility.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateFacilityRequest(
    @NotBlank 
    String name,
    @NotBlank 
    String code
) {
    
}
