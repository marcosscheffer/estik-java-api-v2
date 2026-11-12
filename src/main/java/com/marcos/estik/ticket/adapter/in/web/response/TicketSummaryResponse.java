package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

public record TicketSummaryResponse(
    UUID id,
    TicketFacilityResponse facility,
    String title,
    TicketSpecializationResponse specialization,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
    
}
