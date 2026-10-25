package com.marcos.estik.notification.application.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRolePort;
import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;
import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.shared.domain.event.TicketNotifyEvent;
import com.marcos.estik.shared.domain.user.RoleEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateNotificationUseCase implements CreateNotificationPort {

    private final NotificationRepositoryPort notificationRepositoryPort;
    private final LoadNotificationFindUserByRolePort loadNotificationFindUserByRolePort;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(TicketNotifyEvent event) {
        List<Notification> notifications = new ArrayList<>();

        switch (event.newStatus()) {
            case "OPEN":
                List<UUID> recipientsId = new ArrayList<>(
                    event.typeTicket() == "IT" 
                        ? loadNotificationFindUserByRolePort.execute(RoleEnum.IT)
                        : loadNotificationFindUserByRolePort.execute(RoleEnum.ARCH)
                );

                notifications.addAll(
                    recipientsId.stream().map(
                        recipientId -> Notification.create(
                            recipientId, 
                            "CREATED", 
                            "Created ticket", 
                            "Ticket '" + event.ticketTitle() + "' type: '" + event.typeTicket() + "' created", 
                            event.ticketId()
                        )
                    ).toList()
                );
                break;
            
            case "PROGRESS":
                if (event.assigneeId().equals(event.actorId())) {
                    notifications.add(
                        Notification.create(
                            event.requesterId(),
                            "ASSUMED",
                            "Your ticket has been taken on",
                            "Yout ticket '" + event.ticketTitle() + "' assumed",
                            event.ticketId()
                        )
                    );
                } else {
                    notifications.add(
                        Notification.create(
                            event.assigneeId(),
                            "REOPEN",
                            "Ticket reopened",
                            "Ticket '" + event.ticketTitle() + "' reopened",
                            event.ticketId()
                        )
                    );
                }
                break;
            
            case "CANCELLED":
                notifications.add(
                    Notification.create(
                        event.assigneeId(), 
                        "CANCELLED", 
                        "Ticket cancelled", 
                        "Ticket ''" + event.ticketTitle() + "' you took on has been canceled", 
                        event.ticketId()
                    )
                );
            case "RESOLVED":
                notifications.add(
                    Notification.create(
                        event.requesterId(),
                        "RESOLVED",
                        "Ticket resolved",
                        "your ticket '" + event.ticketTitle() + "' has been resolved",
                        event.ticketId()
                    )
                );
            default:
                break;
        }

        notificationRepositoryPort.saveAll(notifications);
    }
    
}
