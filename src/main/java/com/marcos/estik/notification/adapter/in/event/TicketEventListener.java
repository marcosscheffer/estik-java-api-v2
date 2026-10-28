package com.marcos.estik.notification.adapter.in.event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.marcos.estik.notification.application.command.CreateNotificationCommand;
import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRolePort;
import com.marcos.estik.shared.domain.event.TicketMessageEvent;
import com.marcos.estik.shared.domain.event.TicketNotifyEvent;
import com.marcos.estik.shared.domain.user.RoleEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component 
@Slf4j 
@RequiredArgsConstructor 
public class TicketEventListener {

    private final CreateNotificationPort createNotificationPort;
    private final LoadNotificationFindUserByRolePort loadNotificationFindUserByRolePort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketMessageEventListener(TicketMessageEvent event) {
        UUID recipientsId = event.actorId().equals(event.requesterId())
            ? event.assigneeId()
            : event.requesterId();

        createNotificationPort.execute(
            new CreateNotificationCommand(
                List.of(recipientsId),
                event.ticketId(),
                "TICKET_MESSAGE",
                "new message",
                "new message on ticket" + event.ticketTitle(),
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketStatusChangedEvent(TicketNotifyEvent event) {
        List<UUID> recipientsId = new ArrayList<>();
        CreateNotificationCommand command;

        switch (event.newStatus()) {
            case "OPEN":
                recipientsId = new ArrayList<>(
                    event.typeTicket().equals("IT") 
                        ? loadNotificationFindUserByRolePort.execute(RoleEnum.IT)
                        : loadNotificationFindUserByRolePort.execute(RoleEnum.ARCH)
                );
                command = new CreateNotificationCommand(
                    recipientsId,
                    event.ticketId(),
                    "NEW_TICKET",
                    "Ticket created",
                    "ticket '" + event.ticketTitle() + "' created",
                    event.actorId()
                );
                break;
            
            case "PROGRESS":
                if (event.assigneeId().equals(event.actorId())) {
                    recipientsId.add(event.requesterId());
                    command = new CreateNotificationCommand(
                        recipientsId,
                        event.ticketId(),
                        "TICKET_ASSUMED",
                        "Ticket assumed",
                        "Your ticket '" + event.ticketTitle() + "' assumed by " + event.actorId(),
                        event.actorId()
                    );

                } else {
                    recipientsId.add(event.assigneeId());
                    command = new CreateNotificationCommand(
                        recipientsId,
                        event.ticketId(),
                        "TICKET_REOPEN",
                        "ticket reopened",
                        "Ticket '" + event.ticketTitle() + "' reopened",
                        event.actorId()
                    );
                }
                break;
            
            case "CANCELLED":
                recipientsId = new ArrayList<>(
                    event.typeTicket().equals("IT")
                        ? loadNotificationFindUserByRolePort.execute(RoleEnum.IT)
                        : loadNotificationFindUserByRolePort.execute(RoleEnum.ARCH)
                );
                command = new CreateNotificationCommand(
                    recipientsId,
                    event.ticketId(),
                    "TICKET_CANCELLED",
                    "ticket cancelled",
                    "Ticket '" + event.ticketTitle() + "' cancelled",
                    event.actorId()
                );
                break;
            case "RESOLVED":
                recipientsId.add(event.requesterId());
                command = new CreateNotificationCommand(
                    recipientsId,
                    event.ticketId(),
                    "TICKET_RESOLVED",
                    "ticket resolved",
                    "Your ticket '" + event.ticketTitle() + "' resolved by " + event.assigneeId(),
                    event.actorId()
                );
                break;
            default:
                return;        
        }

        createNotificationPort.execute(command);
    }
}
