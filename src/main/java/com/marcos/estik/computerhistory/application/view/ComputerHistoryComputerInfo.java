package com.marcos.estik.computerhistory.application.view;

import java.time.Instant;
import java.util.UUID;

public record ComputerHistoryComputerInfo(
    UUID id, 
    String name,
    Instant updatedAt
) {
    
}
