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
    private UUID facilitySourceId;
    private UUID facilityDestinationId;
    private UUID departamentSourceId;
    private UUID departamentDestinationId;
    private Long quantity;
    private String description;
    private TypeMovementEnum type;
    private Instant createdAt;

    public static StockMovement in(
        UUID itemId,
        UUID facilitySourceId,
        UUID departamentSourceId,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySourceId,
            null,
            departamentSourceId,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.IN, 
            Instant.now()
        );
    }

    public static StockMovement out(
        UUID itemId,
        UUID facilitySourceId,
        UUID departamentSourceId,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySourceId,
            null,
            departamentSourceId,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.OUT, 
            Instant.now()
        );
    }

    public static StockMovement adjust(
        UUID itemId,
        UUID facilitySourceId,
        UUID departamentSourceId,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySourceId,
            null,
            departamentSourceId,
            null, 
            quantity, 
            description, 
            TypeMovementEnum.ADJUSTMENT, 
            Instant.now()
        );
    }

    public static StockMovement transfer(
        UUID itemId,
        UUID facilitySourceId,
        UUID departamentSourceId,
        UUID facilityDestinationId,
        UUID departamentDestinationId,
        Long quantity,
        String description
    ) {
        return new StockMovement(
            UUID.randomUUID(), 
            itemId, 
            facilitySourceId,
            facilityDestinationId,
            departamentSourceId,
            departamentDestinationId, 
            quantity, 
            description, 
            TypeMovementEnum.TRANSFER, 
            Instant.now()
        );
    };
}
