package com.marcos.estik.notification.application.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.notification.application.command.CreateNotificationCommand;
import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;
import com.marcos.estik.notification.domain.Notification;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateNotificationUseCase implements CreateNotificationPort {

    private final NotificationRepositoryPort notificationRepositoryPort;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(CreateNotificationCommand command) {
        List<Notification> notifications = new ArrayList<>(
            command.recipientsId()
                .stream()
                .map(
                    recipientId -> Notification.create(
                        recipientId, 
                        command.type(), 
                        command.title(), 
                        command.message(),
                        command.referenceId()
                    )
                ).filter(notification -> !notification.getRecipientId().equals(command.actorId()))
                .toList()
        );

        notificationRepositoryPort.saveAll(notifications);
    }
}
