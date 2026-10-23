package com.marcos.estik.ticket.adapter.in.web.response;

import java.util.UUID;

public record TicketFacilityResponse(
    UUID id,
    String name,
    String code
) {
    
}
