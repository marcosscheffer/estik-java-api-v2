package com.marcos.estik.ticket.adapter.in.web.request;


import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketRequest(
    @NotBlank 
    String title,
    @NotBlank
    String description,
    @NotNull 
    UUID specializationId
) {
    
}
