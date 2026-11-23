package com.marcos.estik.supplier.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Supplier {

    private UUID id;
    private String name;
    private Instant createdAt;

    public static Supplier create(String name) {
        return new Supplier(
            UUID.randomUUID(), 
            name, 
            Instant.now()
        );
    }
}
