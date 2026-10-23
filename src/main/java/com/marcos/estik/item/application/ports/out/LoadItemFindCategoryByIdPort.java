package com.marcos.estik.item.application.ports.out;

import java.util.UUID;

import com.marcos.estik.item.application.view.ItemCategoryInfo;

public interface LoadItemFindCategoryByIdPort {
    
    ItemCategoryInfo execute(UUID id);

}
