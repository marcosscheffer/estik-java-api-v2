package com.marcos.estik.notification.adapter.out.sse;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.marcos.estik.notification.adapter.out.sse.mapper.NotificationSseMapper;
import com.marcos.estik.notification.adapter.sse.SseEmitterRegistry;
import com.marcos.estik.notification.application.ports.out.NotificationPushPort;
import com.marcos.estik.notification.domain.Notification;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SseNotificationPushAdapter implements NotificationPushPort {

    private final SseEmitterRegistry sseEmitterRegistry;
    private final NotificationSseMapper notificationSseMapper;
    
    @Override
    public void push(List<Notification> notifications) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override 
                public void afterCommit() {
                    send(notifications);
                }
            });
        } else {
            send(notifications);
        }
    }

    private void send(List<Notification> notifications) {
        for (Notification notification : notifications) {
            sseEmitterRegistry.sentTo(
                notification.getRecipientId(),
                "notification", 
                notificationSseMapper.toPayload(notification)
            );
        }
    }
}
