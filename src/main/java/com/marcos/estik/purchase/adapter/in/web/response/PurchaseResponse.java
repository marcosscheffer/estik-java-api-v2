package com.marcos.estik.purchase.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record PurchaseResponse(
    UUID id,
    PurchaseFileResponse file,
    PurchaseSupplierResponse supplier,
    String code,
    Instant createdAt
) {
    
}
