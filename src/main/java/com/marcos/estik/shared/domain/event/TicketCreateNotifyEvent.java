package com.marcos.estik.shared.domain.event;

import java.util.UUID;

public record TicketCreateNotifyEvent (
    UUID ticketId,
    String ticketTitle,
    UUID actorId
) implements TicketEvent {

}
