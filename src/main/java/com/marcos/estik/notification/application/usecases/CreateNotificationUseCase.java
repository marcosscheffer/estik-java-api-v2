package com.marcos.estik.notification.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

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
        if (command.recipientId().equals(command.actorId())) return;

        Notification notification = Notification.create(
            command.recipientId(), 
            command.type(),
            command.title(), 
            command.message(),
            command.referencesId()
        );

        notificationRepositoryPort.save(notification);
    }
    
}
