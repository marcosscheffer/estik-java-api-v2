package com.marcos.estik.ticket.application.view;

import java.util.UUID;

public record TicketAssigneedInfo(
    UUID id,
    TicketUserInfo user
) {
    
}
