package com.marcos.estik.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

public record TicketStatusChangedEvent(
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    UUID assigneeId,
    UUID actorId,
    String newStatus,
    Instant occurredAt
) {
    
}
