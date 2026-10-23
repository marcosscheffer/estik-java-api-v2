package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.shared.domain.event.TicketStatusChangedEvent;

public interface TicketEventPublisherPort {
    
    void execute(TicketStatusChangedEvent event);

}
