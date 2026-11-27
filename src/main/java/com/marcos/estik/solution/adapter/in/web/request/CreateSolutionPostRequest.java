package com.marcos.estik.solution.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSolutionPostRequest(
    @NotBlank 
    String title,
    @NotBlank 
    String description
) {
    
}
