package com.marcos.estik.ticket.adapter.out.websocket;

import java.time.Instant;
import java.util.UUID;

public record TicketMessagePayload(
    UUID id,
    UUID ticketId,
    Author author,
    String body,
    Instant createdAt
) {
    public record Author(
        UUID id,
        String username
    ) {
    }
}
