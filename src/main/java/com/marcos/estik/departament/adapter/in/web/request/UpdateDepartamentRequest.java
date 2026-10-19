package com.marcos.estik.departament.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateDepartamentRequest(
    @NotBlank 
    String name,
    @NotBlank 
    String code
) {
    
}
