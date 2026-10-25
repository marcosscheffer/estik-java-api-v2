package com.marcos.estik.notification.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.notification.application.ports.in.SearchNotificationsPort;
import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;
import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchNotificationsUseCase implements SearchNotificationsPort {
    
    private final NotificationRepositoryPort notificationRepositoryPort;

    @Override
    public PageResult<Notification> execute(UUID principalId, PageQuery page) {
        return notificationRepositoryPort.searchNotification(principalId, page);
    }   
}
