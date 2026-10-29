package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record TicketMessageResponse(
    UUID id,
    TicketUserResponse author,
    String body,
    Instant createdAt
) {
    
}
