package com.marcos.estik.purchase.application.view;

import java.util.UUID;

public record PurchaseFileInfo(
    UUID id,
    String fileUrl,
    String fileType,
    Long fileSize,
    String fileName
) {
    
}
