package com.marcos.estik.departament.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record DepartamentResponse(
    UUID id,
    DepartamentFacilitySummaryResponse facility,
    String name,
    String code,
    Instant createdAt,
    Instant updatedAt
) {
    
}
