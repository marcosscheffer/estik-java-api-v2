package com.marcos.estik.shared.domain.event;

import java.util.UUID;

public record TicketForwardedNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    UUID forwardedId,
    UUID actorId
) implements TicketEvent {
    
}
