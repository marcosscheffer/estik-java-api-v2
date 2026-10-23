package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.ticket.application.view.TicketFacilityInfo;

public interface LoadTicketFindFacilityByIdPort {

    TicketFacilityInfo execute(UUID id);
    
}
