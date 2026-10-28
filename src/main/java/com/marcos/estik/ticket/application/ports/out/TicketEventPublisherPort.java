package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.shared.domain.event.TicketEvent;

public interface TicketEventPublisherPort {
    
    void execute(TicketEvent event);

}
