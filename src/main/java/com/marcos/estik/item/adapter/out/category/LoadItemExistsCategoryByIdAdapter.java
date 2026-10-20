package com.marcos.estik.item.adapter.out.category;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.category.application.ports.in.ExistsCategoryByIdPort;
import com.marcos.estik.item.application.ports.out.LoadItemExistsCategoryByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadItemExistsCategoryByIdAdapter implements LoadItemExistsCategoryByIdPort {

    private final ExistsCategoryByIdPort existsCategoryByIdPort;
    
    @Override
    public Boolean existsById(UUID categoryId) {
        return existsCategoryByIdPort.execute(categoryId);
    }
    
}
