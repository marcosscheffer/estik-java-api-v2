package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record CreateTicketCommand(
    String title,
    String description,
    TicketTypeEnum ticketType,
    UUID facilityId,
    PriorityEnum priority
) {
    
}
