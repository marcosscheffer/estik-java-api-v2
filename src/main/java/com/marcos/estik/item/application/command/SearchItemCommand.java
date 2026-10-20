package com.marcos.estik.item.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchItemCommand(
    String name,
    String sku,
    UUID categoryId,
    PageQuery page
) {
    
}
