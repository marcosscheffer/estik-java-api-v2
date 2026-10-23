package com.marcos.estik.ticket.application.view;

import com.marcos.estik.ticket.domain.Ticket;

public record TicketView(
    Ticket ticket,
    TicketUserInfo requester,
    TicketUserInfo assignee,
    TicketFacilityInfo facility
) {
    
}
