package com.marcos.estik.stock.application.command;

import java.util.UUID;

public record MovementCommand(
    UUID facilityId, 
    UUID departamentId, 
    UUID itemId,
    Long quantity,
    String description
) {
}
