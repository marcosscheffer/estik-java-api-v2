package com.marcos.estik.computer.adapter.in.web.response;

import java.util.UUID;

public record ComputerFacilitySummaryResponse(
    UUID id,
    String name,
    String code
) {
    
}
