package com.marcos.estik.stock.application.command;

import java.util.UUID;

import com.marcos.estik.shared.domain.file.FileUpload;

public record CreateStockPurchaseCommand(
    UUID supplierId,
    String code,
    FileUpload file
) {
    
}
