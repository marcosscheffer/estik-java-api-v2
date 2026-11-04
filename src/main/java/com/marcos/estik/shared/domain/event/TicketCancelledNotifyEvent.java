package com.marcos.estik.shared.domain.event;

import java.util.UUID;

public record TicketCancelledNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    UUID assigneeId,
    UUID actorId
) implements TicketEvent {

}