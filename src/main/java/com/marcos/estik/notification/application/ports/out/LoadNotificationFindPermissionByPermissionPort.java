package com.marcos.estik.notification.application.ports.out;

import java.util.List;

import com.marcos.estik.notification.application.view.PermissionInfo;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface LoadNotificationFindPermissionByPermissionPort {

    List<PermissionInfo> execute(PermissionEnum permission);
    
}
