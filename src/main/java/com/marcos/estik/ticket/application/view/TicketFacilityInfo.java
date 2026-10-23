package com.marcos.estik.ticket.application.view;

import java.util.UUID;

public record TicketFacilityInfo(
    UUID id,
    String name,
    String code
) {
    
}
