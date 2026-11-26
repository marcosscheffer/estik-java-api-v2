package com.marcos.estik.stock.adapter.in.web.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovementPurchaseRequest(
    @NotNull 
    UUID supplierId,
    @NotBlank 
    String code,
    @NotEmpty 
    List<@Valid MovementRequest> items
) {
    
}
