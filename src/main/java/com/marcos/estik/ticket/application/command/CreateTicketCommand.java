package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

public record CreateTicketCommand(
    String title,
    String description,
    UUID type,
    UUID facilityId,
    PriorityEnum priority
) {
    
}
