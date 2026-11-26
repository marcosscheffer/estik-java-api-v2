package com.marcos.estik.notification.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.notification.application.ports.in.CountUnreadNotificationsPort;
import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CountUnreadNotificationsUseCase implements CountUnreadNotificationsPort {

    private final NotificationRepositoryPort notificationRepositoryPort;

    @Override
    public Long execute(UUID recipientId) {
        return notificationRepositoryPort.countByRecipientIdAndNotRead(recipientId);
    }
}
