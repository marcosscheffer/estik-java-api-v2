package com.marcos.estik.ticket.application.command;

import java.util.UUID;

public record UpdateTicketCommand(
    String title,
    String description,
    UUID type
) {
    
}
