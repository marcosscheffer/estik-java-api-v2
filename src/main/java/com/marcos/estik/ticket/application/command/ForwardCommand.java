package com.marcos.estik.ticket.application.command;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

public record ForwardCommand(
    List<UUID> usersId,
    PriorityEnum priority
) {
    
}
