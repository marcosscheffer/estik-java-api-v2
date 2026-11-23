package com.marcos.estik.purchase.application.command;

import java.util.UUID;

import com.marcos.estik.shared.domain.file.FileUpload;

public record CreatePurchaseCommand(
    UUID supplierId,
    String code,
    FileUpload file
) {
    
}
