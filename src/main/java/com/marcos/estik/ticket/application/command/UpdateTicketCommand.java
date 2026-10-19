package com.marcos.estik.ticket.application.command;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record UpdateTicketCommand(
    String title,
    String description,
    TicketTypeEnum ticketType,
    PriorityEnum priority
) {
    
}
