package com.marcos.estik.ticket.adapter.out.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.domain.event.TicketStatusChangedEvent;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketEventPublisherAdapter implements TicketEventPublisherPort {

    private final ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void execute(TicketStatusChangedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
    
}
