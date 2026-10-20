package com.marcos.estik.item.application.command;

public record CreateItemCommand(
    String name,
    String description,
    String code
) {
    
}
