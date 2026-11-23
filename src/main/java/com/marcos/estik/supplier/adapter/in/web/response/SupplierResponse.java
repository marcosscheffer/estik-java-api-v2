package com.marcos.estik.supplier.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record SupplierResponse(
    UUID id,
    String name,
    Instant createdAt
) {
    
}
