package com.marcos.estik.computer.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.exception.DomainCannotMovedException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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

    public void updateSpecs(
        String cpu,
        String ram,
        OsEnum os,
        StorageTypeEnum storageType,
        String storage
    ) {
        this.cpu = cpu;
        this.ram = ram;
        this.os = os;
        this.storageType = storageType;
        this.storage = storage;
        this.updatedAt =Instant.now();
    }

    public void moveTo(UUID facilityId, UUID departamentId) {
        if (this.facilityId.equals(facilityId) && this.departamentId.equals(departamentId)) {
            throw new DomainCannotMovedException("failed to move computer");
        }

        this.facilityId = facilityId;
        this.departamentId = departamentId;
        this.updatedAt = Instant.now();
    }
}
