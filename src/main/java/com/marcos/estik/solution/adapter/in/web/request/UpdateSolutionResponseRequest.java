package com.marcos.estik.solution.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSolutionResponseRequest(
    @NotBlank 
    @Size(max = 800)
    String message
) {
    
}
