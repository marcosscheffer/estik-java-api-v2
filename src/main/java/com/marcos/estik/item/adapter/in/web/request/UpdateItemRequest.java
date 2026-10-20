package com.marcos.estik.item.adapter.in.web.request;

public record UpdateItemRequest(
    String name,
    String description,
    String code
) {
    
}
