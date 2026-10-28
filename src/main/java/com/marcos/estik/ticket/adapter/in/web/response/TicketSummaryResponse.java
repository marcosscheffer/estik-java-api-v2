package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record TicketSummaryResponse(
    UUID id,
    TicketFacilityResponse facility,
    String title,
    TicketTypeEnum ticketType,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
    
}
