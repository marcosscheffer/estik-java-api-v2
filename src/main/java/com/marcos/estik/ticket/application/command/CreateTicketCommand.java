package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;

public record CreateTicketCommand(
    String title,
    String description,
    UUID facilityId,
    PriorityEnum priority
) {
    
}
