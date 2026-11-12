package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.StatusEnum;

public record TicketHistoryResponse(
    UUID id,
    StatusEnum status,
    Instant createdAt
) {
    
}
