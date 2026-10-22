package com.marcos.estik.stock.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class StockMovement {
    private UUID id;
    private UUID itemId;
    private UUID facilitySource;
    private UUID facilityDestination;
    private UUID departamentSource;
    private UUID departamentDestination;
    private Long quantity;
    private String description;
    private TypeMovementEnum type;
    private Instant createdAt;

    public static StockMovement in(
        UUID itemId,
        UUID facilitySource,
        UUID departamentSource,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySource,
            null,
            departamentSource,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.IN, 
            Instant.now()
        );
    }

    public static StockMovement out(
        UUID itemId,
        UUID facilitySource,
        UUID departamentSource,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySource,
            null,
            departamentSource,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.OUT, 
            Instant.now()
        );
    }

    public static StockMovement adjust(
        UUID itemId,
        UUID facilitySource,
        UUID departamentSource,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySource,
            null,
            departamentSource,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.ADJUSTMENT, 
            Instant.now()
        );
    }

    public static StockMovement transfer(
        UUID itemId,
        UUID facilitySource,
        UUID departamentSource,
        UUID facilityDestination,
        UUID departamentDestination,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySource,
            facilityDestination,
            departamentSource,
            departamentDestination, 
            quantity, 
            description, 
            TypeMovementEnum.TRANSFER, 
            Instant.now()
        );
    };
}
