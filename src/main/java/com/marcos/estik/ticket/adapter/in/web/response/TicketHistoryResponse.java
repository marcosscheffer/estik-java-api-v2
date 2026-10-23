package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record TicketHistoryResponse(
    UUID id,
    StatusEnum status,
    TicketTypeEnum type,
    Instant createdAt
) {
    
}
