package com.marcos.estik.solution.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSolutionPostRequest(
    @NotBlank 
    @Size(max = 100)
    String title,
    @NotBlank 
    @Size(max = 800)
    String description
) {
    
}
