package com.marcos.estik.departament.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter 
public class Departament {
    private UUID id;
    private UUID facilityId;
    private String name;
    private String code;
    private Instant createdAt;
    private Instant updatedAt;

    public static Departament create(
        UUID facilityId,
        String name,
        String code
    ) {
        return new Departament(
            UUID.randomUUID(),
            facilityId,
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
