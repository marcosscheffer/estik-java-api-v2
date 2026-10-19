package com.marcos.estik.computerhistory.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class ComputerHistory {
    UUID id;
    String serviceDescription;
    DirectionEnum direction;
    UUID computerId;
    UUID departamentId;
    Instant createdAt;

    public static ComputerHistory create(
        String serviceDescription,
        DirectionEnum direction,
        UUID computerId,
        UUID departamentId
    ) {
        return new ComputerHistory(
            UUID.randomUUID(), 
            serviceDescription, 
            direction, 
            computerId,
            departamentId,
            Instant.now()
        );
    }
}
