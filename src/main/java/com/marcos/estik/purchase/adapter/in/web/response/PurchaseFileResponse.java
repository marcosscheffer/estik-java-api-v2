package com.marcos.estik.purchase.adapter.in.web.response;

import java.util.UUID;

public record PurchaseFileResponse(
    UUID id,
    String fileName,
    String fileUrl,
    String fileType
) {
    
}
