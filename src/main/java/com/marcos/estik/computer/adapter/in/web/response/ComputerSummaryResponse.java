package com.marcos.estik.computer.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ComputerSummaryResponse(
    UUID id,
    String name,
    UUID facilityId,
    UUID departamentId,
    Instant createdAt,
    Instant updatedAt
) {
    
}
