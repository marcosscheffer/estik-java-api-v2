package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record CreateTicketCommand(
    String title,
    String description,
    TicketTypeEnum ticketType,
    UUID facilityId,
    PriorityEnum priority
) {
    
}
