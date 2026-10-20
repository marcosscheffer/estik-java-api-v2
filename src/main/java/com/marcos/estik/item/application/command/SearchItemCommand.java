package com.marcos.estik.item.application.command;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchItemCommand(
    String name,
    String code,
    PageQuery page
) {
    
}
