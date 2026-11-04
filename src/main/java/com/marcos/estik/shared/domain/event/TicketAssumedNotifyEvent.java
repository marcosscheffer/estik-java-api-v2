package com.marcos.estik.shared.domain.event;

import java.util.UUID;

public record TicketAssumedNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    UUID actorId
) implements TicketEvent {
}