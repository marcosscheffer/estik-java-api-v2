package com.marcos.estik.ticket.adapter.in.web.request;


import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketRequest(
    @NotBlank 
    String title,
    @NotBlank
    String description,
    @NotNull 
    TicketTypeEnum ticketType
) {
    
}
