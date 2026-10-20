package com.marcos.estik.item.application.command;

public record UpdateItemCommand(
    String name,
    String description,
    String code
) {
    
}
