package com.marcos.estik.ticket.application.command;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record UpdateTicketCommand(
    String title,
    String description,
    TicketTypeEnum ticketType,
    PriorityEnum priority
) {
    
}
