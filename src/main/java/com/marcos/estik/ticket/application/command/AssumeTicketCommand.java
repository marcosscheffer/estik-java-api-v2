package com.marcos.estik.ticket.application.command;

import java.util.UUID;

public record AssumeTicketCommand(
    UUID ticketId,
    UUID principal
) {
    
}
