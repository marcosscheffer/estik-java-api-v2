package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;

public record TicketSummaryResponse(
    UUID id,
    String title,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
    
}
