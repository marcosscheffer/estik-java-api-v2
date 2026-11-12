package com.marcos.estik.notification.adapter.out.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.in.FindPermissionByPermissionPort;
import com.marcos.estik.notification.application.ports.out.LoadNotificationFindPermissionByPermissionPort;
import com.marcos.estik.notification.application.view.NotificationPermissionInfo;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadNotificationFindPermissionByPermissionAdapter implements  LoadNotificationFindPermissionByPermissionPort {
    
    private final FindPermissionByPermissionPort findPermissionByPermissionPort;
    private final NotificationPermissionMapper notificationPermissionMapper;
    
    @Override
    public List<NotificationPermissionInfo> execute(Set<PermissionEnum> permissions) {
        List<NotificationPermissionInfo> result = new ArrayList<>();

        permissions.forEach(
            permission -> findPermissionByPermissionPort.execute(permission)
                .stream()
                .map(
                    notificationPermissionMapper::toInfo
                )
                .forEach(result::add)
        );

        return result;
    }
}
