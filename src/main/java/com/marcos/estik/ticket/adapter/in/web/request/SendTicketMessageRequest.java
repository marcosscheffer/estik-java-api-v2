package com.marcos.estik.ticket.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record SendTicketMessageRequest(
    @NotBlank 
    String body
) {
    
}
