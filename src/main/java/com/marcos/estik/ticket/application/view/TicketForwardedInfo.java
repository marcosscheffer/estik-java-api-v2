package com.marcos.estik.ticket.application.view;

import java.util.UUID;

public record TicketForwardedInfo(
    UUID id,
    TicketUserInfo user
) {
    
}
