package com.marcos.estik.ticket.application.view;

import java.util.List;

import com.marcos.estik.ticket.domain.Ticket;

public record TicketView(
    Ticket ticket,
    TicketUserInfo requester,
    TicketUserInfo regulator,
    TicketFacilityInfo facility,
    TicketSpecializationInfo specialization,
    List<TicketForwardedInfo> forwarded,
    List<TicketAssigneedInfo> asigneeds
) {
    
}
