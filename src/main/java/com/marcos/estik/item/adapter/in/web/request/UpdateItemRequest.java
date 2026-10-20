package com.marcos.estik.item.adapter.in.web.request;

import java.util.UUID;

public record UpdateItemRequest(
    String name,
    String description,
    String sku,
    Long minimum,
    UUID categoryId
) {

}
