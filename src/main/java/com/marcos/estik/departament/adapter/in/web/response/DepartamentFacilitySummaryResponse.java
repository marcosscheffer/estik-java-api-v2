package com.marcos.estik.departament.adapter.in.web.response;

import java.util.UUID;

public record DepartamentFacilitySummaryResponse(
    UUID id,
    String name,
    String code
) {
    
}
