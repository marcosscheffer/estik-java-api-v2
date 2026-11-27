package com.marcos.estik.solution.adapter.in.web.request;

import java.util.UUID;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSolutionResponseRequest(
    @NotNull 
    UUID solutionPostId,
    @NotBlank 
    String message
) {
    
}
