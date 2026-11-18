package com.marcos.estik.item.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Item {
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;

    public static Item create(
        String name,
        String description,
        String sku,
        UUID categoryId
    ) {
        return new Item(
            UUID.randomUUID(),
            name,
            description,
            sku,
            categoryId,
            Instant.now(),
            Instant.now()
        );
    }

    public void updateInfo(
        String name,
        String description,
        String sku,
        UUID categoryId
    ) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.categoryId = categoryId;
        this.updatedAt = Instant.now();
    }
}
