package com.marcos.estik.stock.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record MovementRequest(
    @NotNull 
    UUID facilityId, 
    @NotNull 
    UUID departamentId, 
    @NotNull 
    UUID itemId,
    @NotNull 
    Long quantity,
    String description
) {
    
}
