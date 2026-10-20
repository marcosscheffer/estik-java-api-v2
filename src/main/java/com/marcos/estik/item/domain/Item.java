package com.marcos.estik.item.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Item {
    UUID id;
    String name;
    String description;
    String code;
    Instant createdAt;
    Instant updatedAt;

    public static Item create(
        String name,
        String description,
        String code
    ) {
        return new Item(
            UUID.randomUUID(),
            name,
            description,
            code,
            Instant.now(),
            Instant.now()
        );
    }

    public void updateInfo(
        String name,
        String description,
        String code
    ) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.updatedAt = Instant.now();
    }
}
