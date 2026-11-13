package com.marcos.estik.ticket.application.command;

import java.util.UUID;

public record CreateForwardedCommand(
    UUID ticketId,
    UUID userId
) {
    
}
