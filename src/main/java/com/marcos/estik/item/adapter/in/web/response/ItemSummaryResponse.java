package com.marcos.estik.item.adapter.in.web.response;

import java.util.UUID;

public record ItemSummaryResponse(
    UUID id,
    String name,
    String sku
) {
    
}
