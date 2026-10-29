package com.marcos.estik.notification.application.ports.out;

import com.marcos.estik.notification.domain.Notification;

public interface NotificationPushPort {
    
    void execute(Notification notification);

}
