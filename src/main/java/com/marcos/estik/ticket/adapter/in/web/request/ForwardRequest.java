package com.marcos.estik.ticket.adapter.in.web.request;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

import jakarta.validation.constraints.NotNull;

public record ForwardRequest(
    @NotNull 
    List<UUID> usersId,
    @NotNull 
    PriorityEnum priority

) {
    
}
