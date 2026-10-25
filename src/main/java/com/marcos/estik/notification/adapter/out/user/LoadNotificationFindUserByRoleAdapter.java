package com.marcos.estik.notification.adapter.out.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRolePort;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.application.port.in.FindUserByRolePort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadNotificationFindUserByRoleAdapter implements LoadNotificationFindUserByRolePort {

    private final FindUserByRolePort findUserByRolePort;
    
    @Override
    public List<UUID> execute(RoleEnum role) {
        return findUserByRolePort.execute(role).stream().map(user -> user.getId()).toList();
    }
}
