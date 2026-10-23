package com.marcos.estik.item.application.view;

import com.marcos.estik.item.domain.Item;

public record ItemView(
    Item item,
    ItemCategoryInfo category
) {
    
}
