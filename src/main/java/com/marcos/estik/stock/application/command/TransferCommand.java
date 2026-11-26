package com.marcos.estik.stock.application.command;

import java.util.UUID;

public record TransferCommand(
    UUID stockBalanceId,
    UUID facilityDestinationId,
    UUID departamentDestinationId,
    Long quantity,
    String description
) {
    
}
