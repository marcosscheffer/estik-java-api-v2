package com.marcos.estik.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

public record TicketMessageEvent(
    UUID id,
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    UUID assigneeId,
    UUID actorId,
    String ticketType,
    Instant createdAt
) implements TicketEvent {
}

