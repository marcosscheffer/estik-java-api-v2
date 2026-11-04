package com.marcos.estik.shared.domain.event;

public sealed interface TicketEvent 
    permits 
        TicketCreateNotifyEvent, 
        TicketCancelledNotifyEvent, 
        TicketAssumedNotifyEvent,
        TicketForwardedNotifyEvent,
        TicketReopenNotifyEvent,
        TicketResolveNotifyEvent {
    
}
