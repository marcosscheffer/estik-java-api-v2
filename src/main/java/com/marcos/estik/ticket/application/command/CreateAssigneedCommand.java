package com.marcos.estik.ticket.application.command;

import java.util.UUID;

public record CreateAssigneedCommand(
    UUID userId,
    UUID ticketId
) {
    
}
