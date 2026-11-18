package com.marcos.estik.category.domain;

import java.util.UUID;

import com.marcos.estik.category.domain.exception.MinimumInvalidException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Category {
    private UUID id;
    private String name;
    private String description;
    private Long minimum;

    public static Category create(
        String name,
        String description,
        Long minimum
    ) {
        if (minimum <= 0) {
            throw new MinimumInvalidException("minimum should never be less than 1");
        }
        
        return new Category(
            UUID.randomUUID(),
            name,
            description,
            minimum
        );
    }

    public void updateInfo(
        String name,
        String description,
        Long minimum
    ) {
        if (minimum <= 0) {
            throw new MinimumInvalidException("minimum should never be less than 1");
        }
        this.name = name;
        this.description = description;
        this.minimum = minimum;
    }
    
}
