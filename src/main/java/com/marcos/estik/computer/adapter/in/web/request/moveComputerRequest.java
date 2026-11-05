package com.marcos.estik.computer.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.computer.domain.DirectionEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record moveComputerRequest(
    @NotNull 
    UUID facilityId,
    @NotNull 
    UUID departamentId,
    @NotBlank 
    String serviceDescription,
    @NotNull 
    DirectionEnum direction
) {
    
}
