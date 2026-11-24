package com.marcos.estik.stock.application.command;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.shared.domain.file.FileUpload;


public record EntryPurchaseCommand(
    UUID supplierId,
    String code,
    FileUpload file,
    List<MovementCommand> items
) {
    
}
