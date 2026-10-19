package com.marcos.estik.facility.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Facility {
    private UUID id;
    private String name;
    private String code;
    
    private Instant createdAt;
    private Instant updatedAt;

    public static Facility create(
        String name,
        String code
    ) {
        return new Facility(
            UUID.randomUUID(),
            name,
            code,
            Instant.now(),
            Instant.now()
        );
    }

    public void updateInfo(
        String name,
        String code
    ) {
        this.name = name;
        this.code = code;
        this.updatedAt = Instant.now();
    }
}
