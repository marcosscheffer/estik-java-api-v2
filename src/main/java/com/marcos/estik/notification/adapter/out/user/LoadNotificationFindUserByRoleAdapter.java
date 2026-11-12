package com.marcos.estik.notification.adapter.out.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRolesPort;
import com.marcos.estik.notification.application.view.NotificationUserInfo;
import com.marcos.estik.user.application.port.in.FindUserByRolePort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadNotificationFindUserByRoleAdapter implements LoadNotificationFindUserByRolesPort {

    private final FindUserByRolePort findUserByRolePort;
    private final NotificationUserMapper notificationUserMapper;
    
    @Override
    public List<NotificationUserInfo> execute(List<UUID> role) {
        return findUserByRolePort.execute(role).stream().map(notificationUserMapper::toInfo).toList();
    }
}
