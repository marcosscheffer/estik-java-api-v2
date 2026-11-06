package com.marcos.estik.access.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Role {
    private UUID id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public static Role create(
        String name
    ) {
        return new Role(
            UUID.randomUUID(), 
            name.toUpperCase(), 
            Instant.now(), 
            Instant.now()    
        );
    }

}
