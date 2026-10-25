package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.shared.domain.event.TicketNotifyEvent;

public interface TicketEventPublisherPort {
    
    void execute(TicketNotifyEvent event);

}
