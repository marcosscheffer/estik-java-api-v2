package com.marcos.estik.item.adapter.out.category;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.category.application.ports.in.FindCategoryByIdPort;
import com.marcos.estik.item.application.ports.out.LoadItemFindCategoryByIdPort;
import com.marcos.estik.item.application.view.ItemCategoryInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadItemFindCategoryByIdAdapter implements LoadItemFindCategoryByIdPort {
    
    private final FindCategoryByIdPort findCategoryByIdPort;
    private final ItemCategoryMapper itemCategoryMapper;
    
    @Override
    public ItemCategoryInfo execute(UUID id) {
        return itemCategoryMapper.toCategoryInfo(findCategoryByIdPort.execute(id));
    }
}
