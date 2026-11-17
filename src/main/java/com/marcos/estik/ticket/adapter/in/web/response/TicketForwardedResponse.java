package com.marcos.estik.ticket.adapter.in.web.response;

import java.util.UUID;

public record TicketForwardedResponse(
    UUID id,
    TicketUserResponse user
) {
    
}
