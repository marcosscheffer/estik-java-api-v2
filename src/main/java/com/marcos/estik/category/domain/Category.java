package com.marcos.estik.category.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Category {
    private UUID id;
    private String name;
    private String description;

    public static Category create(
        String name,
        String description
    ) {
        return new Category(
            UUID.randomUUID(),
            name,
            description    
        );
    }

    public void updateInfo(
        String name,
        String description
    ) {
        this.name = name;
        this.description = description;
    }
    
}
