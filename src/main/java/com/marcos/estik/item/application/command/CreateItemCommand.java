package com.marcos.estik.item.application.command;

import java.util.UUID;

public record CreateItemCommand(
    String name,
    String description,
    String sku,
    Long minimum,
    UUID categoryId
) {
    
}
