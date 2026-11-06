package com.marcos.estik.access.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Role {
    UUID id;
    String name;
    Instant createdAt;
    Instant updatedAt;

    public Role create(
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
