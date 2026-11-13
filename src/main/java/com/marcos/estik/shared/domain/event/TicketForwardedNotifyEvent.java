package com.marcos.estik.shared.domain.event;

import java.util.List;
import java.util.UUID;

public record TicketForwardedNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    UUID requesterId,
    List<UUID> forwardedId,
    UUID actorId
) implements TicketEvent {
    
}
