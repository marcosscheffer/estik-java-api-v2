package com.marcos.estik.ticket.adapter.out.event;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.marcos.estik.ticket.adapter.out.websocket.TicketMessagePayload;
import com.marcos.estik.ticket.application.ports.out.TicketMessagePushPort;
import com.marcos.estik.ticket.application.view.TicketMessageView;
import com.marcos.estik.ticket.domain.TicketMessage;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class StompTicketMessagePushAdapter implements TicketMessagePushPort {
    
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void push(TicketMessageView ticketMessage) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override 
                public void afterCommit() {
                    send(ticketMessage);
                }
            });
        } else {
            send(ticketMessage);
        }
    }

    private void send(TicketMessageView ticketMessage) {
        TicketMessage message = ticketMessage.ticketMessage();

        simpMessagingTemplate.convertAndSend(
            "/topic/tickets/" + message.getTicketId() + "messages",
            new TicketMessagePayload(
                message.getId(),
                message.getTicketId(),
                new TicketMessagePayload.Author(ticketMessage.user().id(), ticketMessage.user().username()),
                message.getBody(),
                message.getCreatedAt()
            )
        );
    }
}
