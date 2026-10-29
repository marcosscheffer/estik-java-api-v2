package com.marcos.estik.ticket.application.view;

import com.marcos.estik.ticket.domain.TicketMessage;

public record TicketMessageView(
    TicketMessage ticketMessage,
    TicketUserInfo author
) {
    
}
