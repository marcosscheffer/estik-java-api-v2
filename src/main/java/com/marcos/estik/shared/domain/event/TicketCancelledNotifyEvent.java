package com.marcos.estik.shared.domain.event;

import java.util.List;
import java.util.UUID;

public record TicketCancelledNotifyEvent(
    UUID ticketId,
    String ticketTitle,
    List<UUID> assigneesId,
    UUID actorId
) implements TicketEvent {

}