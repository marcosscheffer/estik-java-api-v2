package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record TicketMessageResponse(
    UUID id,
    TicketUserResponse user,
    String body,
    Instant createdAt
) {
    
}
