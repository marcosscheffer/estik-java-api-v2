package com.marcos.estik.stock.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record TransferRequest(
    @NotNull 
    UUID stockBalanceId,
    @NotNull 
    UUID facilityDestinationId,
    @NotNull 
    UUID departamentDestinationId,
    @NotNull 
    UUID itemId,
    @NotNull 
    Long quantity,
    String description
) {
    
}
