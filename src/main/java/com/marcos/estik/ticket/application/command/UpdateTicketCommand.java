package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record UpdateTicketCommand(
    String title,
    String description,
    UUID facilityId,
    UUID assigneeId,
    StatusEnum status,
    TicketTypeEnum ticketType,
    PriorityEnum priority
) {
    
}
