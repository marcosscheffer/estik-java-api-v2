package com.marcos.estik.notification.adapter.out.sse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.marcos.estik.notification.adapter.in.sse.SseNotificationEmitter;
import com.marcos.estik.notification.application.ports.out.NotificationPushPort;
import com.marcos.estik.notification.domain.Notification;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class NotificationPushAdapter implements NotificationPushPort {

    private final NotificationSseMapper notificationSseMapper;

    @Override
    public void execute(Notification notification) {
        if(TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    send(notification);
                }
            });
        } else{
            send(notification);
        }
    }

    public void send(Notification notification) {
        SseNotificationEmitter.sendTo(
            notification.getRecipientId(),
            "notification",
            notificationSseMapper.toPayload(notification)    
        );
    }
}
