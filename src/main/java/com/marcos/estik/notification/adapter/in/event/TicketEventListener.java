package com.marcos.estik.notification.adapter.in.event;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.marcos.estik.notification.application.command.CreateNotificationCommand;
import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.shared.domain.event.TicketStatusChangedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component 
@Slf4j 
@RequiredArgsConstructor 
public class TicketEventListener {

    private final CreateNotificationPort createNotificationPort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketStatusChangedEvent(TicketStatusChangedEvent event) {
        try {
            switch (event.newStatus()) {
            case "PROGRESS" -> {
                Boolean assumed = event.actorId().equals(event.assigneeId());

                if (assumed) {
                    notify(
                        event.requesterId(), 
                        event, 
                        "TICKET_ASSIGNED",
                        "Ticket assumed",
                        "Your ticket '" + event.ticketTitle() + "' assumed"
                    );
                } else {
                    notify(
                        event.requesterId(), 
                        event, 
                        "TICKET_REOPENED",
                        "Ticket reopen",
                        "Your ticket '" + event.ticketTitle() + "' reopened"
                    );
                }
            }
            case "CANCELLED" -> notify(
                event.assigneeId(),
                event,
                "TICKET_CANCELLED",
                "Ticket cancelled",
                "Ticket '" + event.ticketTitle() + "' cancelled"
            );
            case "RESOLVED" -> notify(
                event.requesterId(),
                event,
                "TICKET_RESOLVED",
                "Ticket resolved",
                "Your ticket '" + event.ticketTitle() + "' resolved"
            );                
            default -> {}
            }
        } catch(Exception e) {
            log.error("Creation of notifiation failed {}", event.ticketTitle(), e);
        }
    }

    private void notify(
        UUID recipientId, 
        TicketStatusChangedEvent event, 
        String type, 
        String title, 
        String message
    ) {
        if(recipientId == null) return;

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientId,
                event.actorId(),
                type,
                title,
                message,
                event.ticketId()
            )
        );

    }
}
