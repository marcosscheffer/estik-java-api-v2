package com.marcos.estik.item.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.item.domain.exception.ItemMinimumInvalidException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Item {
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private Long minimum;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;

    public static Item create(
        String name,
        String description,
        String sku,
        Long minimum,
        UUID categoryId
    ) {
        return new Item(
            UUID.randomUUID(),
            name,
            description,
            sku,
            minimum,
            categoryId,
            Instant.now(),
            Instant.now()
        );
    }

    public void updateInfo(
        String name,
        String description,
        String sku,
        Long minimum,
        UUID categoryId
    ) {
        if (minimum <= 0) {
            throw new ItemMinimumInvalidException("minimum should never be less than 1");
        }

        this.name = name;
        this.description = description;
        this.sku = sku;
        this.minimum = minimum;
        this.categoryId = categoryId;
        this.updatedAt = Instant.now();
    }
}
