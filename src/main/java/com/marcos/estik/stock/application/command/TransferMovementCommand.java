package com.marcos.estik.stock.application.command;

import java.util.UUID;

public record TransferMovementCommand(
    UUID facilityDestination,
    UUID departamentDestination,
    UUID itemId,
    Long quantity,
    String description
) {
    
}
