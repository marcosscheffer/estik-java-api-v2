package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

public record ForwardCommand(
    UUID userId,
    PriorityEnum priority
) {
    
}
