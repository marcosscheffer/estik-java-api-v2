package com.marcos.estik.notification.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.notification.application.ports.in.MarkReadNotificationPort;
import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;
import com.marcos.estik.notification.domain.Notification;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class MarkReadNotificationUseCase implements MarkReadNotificationPort {
    
    private final NotificationRepositoryPort notificationRepositoryPort;
    
    @Override
    public void execute(UUID notificationId, UUID principalId) {
        Notification notification = notificationRepositoryPort.findById(notificationId);
        notification.markAsRead(principalId);
        notificationRepositoryPort.save(notification);
    }
    
}
