package com.marcos.estik.notification.adapter.in.event;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.marcos.estik.notification.application.command.CreateNotificationCommand;
import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRolePort;
import com.marcos.estik.shared.domain.event.TicketAssumedNotifyEvent;
import com.marcos.estik.shared.domain.event.TicketCancelledNotifyEvent;
import com.marcos.estik.shared.domain.event.TicketCreateNotifyEvent;
import com.marcos.estik.shared.domain.event.TicketForwardedNotifyEvent;
import com.marcos.estik.shared.domain.event.TicketReopenNotifyEvent;
import com.marcos.estik.shared.domain.event.TicketResolveNotifyEvent;
import com.marcos.estik.shared.domain.user.RoleEnum;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketEventListener {

    private final CreateNotificationPort createNotificationPort;
    private final LoadNotificationFindUserByRolePort loadNotificationFindUserByRolePort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketCreateNotifyEventListener(TicketCreateNotifyEvent event) {
        // List<UUID> recipientsId = loadNotificationFindUserByRolePort.execute(RoleEnum.ADMINISTRATOR);

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_CREATED",
                "Ticket created",
                "new message on ticket" + event.ticketTitle(),
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketCancelledNotifyEvent(TicketCancelledNotifyEvent event) {
        List<UUID> recipientsId = event == null ? List.of() : List.of(event.assigneeId());

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_CANCELLED",
                "Ticket cancelled",
                "Ticket '" + event.ticketTitle() + "' cancelled",
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketAssumedNotifyEvent(TicketAssumedNotifyEvent event) {
        List<UUID> recipientsId = List.of(event.requesterId());

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_ASSUMED",
                "Ticket assumed",
                "Ticket '" + event.ticketTitle() + "' assumed",
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketForwardedNotifyEvent(TicketForwardedNotifyEvent event) {
        List<UUID> recipientsId = List.of(event.requesterId(), event.forwardedId());

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_FORWARDED",
                "Ticket forwarded",
                "Ticket '" + event.ticketTitle() + "' forwarded",
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketReopenNotifyEvent(TicketReopenNotifyEvent event) {
        List<UUID> recipientsId = List.of(event.assigneeId());

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_REOPENED",
                "Ticket reopened",
                "Ticket '" + event.ticketTitle() + "' reopened",
                event.actorId()
            )
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketResolveNotifyEven(TicketResolveNotifyEvent event) {
        List<UUID> recipientsId = List.of(event.requesterId());

        createNotificationPort.execute(
            new CreateNotificationCommand(
                recipientsId,
                event.ticketId(),
                event.ticketTitle(),
                "TICKET_RESOLVED",
                "Ticket resolved",
                "Ticket '" + event.ticketTitle() + "' resolved",
                event.actorId()
            )
        );
    }
}
