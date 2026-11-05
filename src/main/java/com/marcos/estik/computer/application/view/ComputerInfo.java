package com.marcos.estik.computer.application.view;

import java.time.Instant;
import java.util.UUID;

public record ComputerInfo(
    UUID id, 
    String name,
    Instant updatedAt
) {
    
}
