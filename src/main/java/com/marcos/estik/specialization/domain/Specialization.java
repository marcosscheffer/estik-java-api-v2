package com.marcos.estik.specialization.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Specialization {
    UUID id;
    String name;
    Instant createdAt;

    public static Specialization create(
        String name
    ) {
        return new Specialization(
            UUID.randomUUID(), 
            name, 
            Instant.now()
        );
    } 

    public void updateName(String name) {
        this.name = name;
    }
}
