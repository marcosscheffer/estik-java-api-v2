package com.marcos.estik.item.application.command;

import java.util.UUID;

public record UpdateItemCommand(
    String name,
    String description,
    String sku,
    UUID categoryId
) {
    
}
