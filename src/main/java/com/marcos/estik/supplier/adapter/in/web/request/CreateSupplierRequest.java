package com.marcos.estik.supplier.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSupplierRequest(
    @NotBlank 
    String name
) {
    
}
