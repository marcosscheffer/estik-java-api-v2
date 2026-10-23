package com.marcos.estik.computer.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ComputerSummaryResponse(
    UUID id,
    String name,
    ComputerDepartamentSummaryResponse departament,
    ComputerFacilitySummaryResponse facility,
    Instant createdAt,
    Instant updatedAt
) {
    
}
