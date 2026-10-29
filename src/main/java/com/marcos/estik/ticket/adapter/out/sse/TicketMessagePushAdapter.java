package com.marcos.estik.ticket.adapter.out.sse;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.adapter.in.sse.SseTicketMessageEmitter;
import com.marcos.estik.ticket.application.ports.out.TicketMessagePushPort;
import com.marcos.estik.ticket.application.view.TicketMessageView;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TicketMessagePushAdapter implements TicketMessagePushPort {

    private final TicketMessageSseMapper ticketMessageSseMapper;
    
    @Override
    public void execute(TicketMessageView ticketMessage) {
        SseTicketMessageEmitter.sendTo(
            ticketMessage.ticketMessage().getTicketId(), 
            "ticket_message", 
            ticketMessageSseMapper.toPayload(ticketMessage)
        );
    }
}