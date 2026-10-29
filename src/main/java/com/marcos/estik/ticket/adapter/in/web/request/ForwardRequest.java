package com.marcos.estik.ticket.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

import jakarta.validation.constraints.NotNull;

public record ForwardRequest(
    @NotNull 
    UUID userId,
    @NotNull 
    PriorityEnum priority

) {
    
}
