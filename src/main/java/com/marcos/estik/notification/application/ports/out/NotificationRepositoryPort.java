package com.marcos.estik.notification.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface NotificationRepositoryPort {
    
    Notification save(Notification notification);

    PageResult<Notification> searchNotification(UUID recipientId, PageQuery page);

    List<Notification> saveAll(UUID recipientId);

    int countUnread(UUID recipientId);
    
}
