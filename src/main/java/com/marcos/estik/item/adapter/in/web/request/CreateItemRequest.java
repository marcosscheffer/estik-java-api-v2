package com.marcos.estik.item.adapter.in.web.request;

public record CreateItemRequest(
    String name,
    String description,
    String code
) {
    
}
