package com.marcos.estik.ticket.adapter.out.sse;

import java.util.UUID;

public record TicketMessagePayload(
    UUID id,
    UUID ticketId,
    UUID authorId,
    String body,
    String createdAt
) {

}