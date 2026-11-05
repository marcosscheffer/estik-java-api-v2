package com.marcos.estik.computer.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ComputerHistoryComputerResponse(
    UUID id,
    String name,
    Instant updatedAt
) {
    
}
