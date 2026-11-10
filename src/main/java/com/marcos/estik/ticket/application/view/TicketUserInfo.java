package com.marcos.estik.ticket.application.view;

import java.util.UUID;

public record TicketUserInfo(
    UUID id,
    String username,
    UUID roleId
) {
    
}
