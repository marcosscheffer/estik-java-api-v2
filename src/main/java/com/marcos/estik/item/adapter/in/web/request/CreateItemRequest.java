package com.marcos.estik.item.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateItemRequest(
    @NotBlank 
    String name,
    @NotBlank 
    String description,
    @NotBlank 
    String sku,
    @NotNull 
    Long minimum,
    @NotNull 
    UUID categoryId
) {
    
}
