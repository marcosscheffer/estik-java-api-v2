package com.marcos.estik.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

public record TicketNotifyEvent (
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    UUID forwarded,
    UUID assigneeId,
    UUID actorId,
    String typeTicket,
    String newStatus,
    Instant occurredAt
) implements TicketEvent {
    
}
