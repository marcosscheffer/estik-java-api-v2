package com.marcos.estik.computer.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor 
public class Computer {
    UUID id;
    String name;
    String cpu;
    String ram;
    OsEnum os;
    StorageTypeEnum storageType;
    String storage;
    UUID facilityId;
    UUID departamentId;
    Instant createdAt;
    Instant updatedAt;

    public static Computer create(
        String name,
        String cpu,
        String ram,
        OsEnum os,
        StorageTypeEnum storageType,
        String storage,
        UUID facilityId,
        UUID departamentId
    ) {
        return new Computer(
            UUID.randomUUID(),
            name,
            cpu,
            ram,
            os,
            storageType,
            storage,
            facilityId,
            departamentId,
            Instant.now(),
            Instant.now()
        );
    }
}
