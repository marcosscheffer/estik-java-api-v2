package com.marcos.estik.purchase.adapter.in.web.response;

import java.util.UUID;

public record PurchaseSummaryResponse(
    UUID id,
    PurchaseSupplierResponse supplier,
    String code
) {
    
}
