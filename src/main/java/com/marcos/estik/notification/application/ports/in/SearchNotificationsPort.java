package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;

import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchNotificationsPort {
    
    PageResult<Notification> execute(UUID principalId, PageQuery page);

}
