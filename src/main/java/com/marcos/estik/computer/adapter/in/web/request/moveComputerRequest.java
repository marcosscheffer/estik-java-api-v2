package com.marcos.estik.computer.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record moveComputerRequest(
    @NotNull 
    UUID facilityId,
    @NotNull 
    UUID departamentId,
    @NotBlank 
    String serviceDescription,
    @NotBlank 
    String direction
) {
    
}
