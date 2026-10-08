package com.marcos.estik.ticket.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTicketRequest(
    @NotBlank 
    String title,
    String description,
    @NotNull 
    UUID facilityId,
    @NotNull 
    PriorityEnum priority
) {
    
}
