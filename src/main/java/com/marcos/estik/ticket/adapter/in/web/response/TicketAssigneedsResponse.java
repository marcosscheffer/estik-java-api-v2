package com.marcos.estik.ticket.adapter.in.web.response;

import java.util.UUID;

public record TicketAssigneedsResponse(
    UUID id,
    TicketUserResponse user
) {
    
}
