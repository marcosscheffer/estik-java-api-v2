package com.marcos.estik.notification.application.ports.out;

import java.util.List;
import java.util.Set;

import com.marcos.estik.notification.application.view.NotificationPermissionInfo;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface LoadNotificationFindPermissionByPermissionPort {

    List<NotificationPermissionInfo> execute(Set<PermissionEnum> permission);
    
}
