package com.marcos.estik.departament.adapter.in.web.response;

import java.util.UUID;

public record DepartamentSummaryResponse(
    UUID id,
    UUID facilityId,
    String name,
    String code
) {
    
}
