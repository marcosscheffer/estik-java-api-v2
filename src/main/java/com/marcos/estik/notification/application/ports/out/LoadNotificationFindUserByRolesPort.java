package com.marcos.estik.notification.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.notification.application.view.NotificationUserInfo;


public interface LoadNotificationFindUserByRolesPort {
    
    List<NotificationUserInfo> execute(List<UUID> role);

}
