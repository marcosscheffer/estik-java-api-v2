package com.marcos.estik.shared.domain.event;

import java.util.List;
import java.util.UUID;

public record TicketReopenNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    List<UUID> assigneeId,
    UUID actorId
) implements TicketEvent {
    
}
