package com.marcos.estik.notification.adapter.out.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.notification.application.ports.out.LoadNotificationFindUserByRoleAndFunctionPort;
import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.application.port.in.FindUserByRoleAndFunctionPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadNotificationFindUserByRoleAndFunctionAdapter implements LoadNotificationFindUserByRoleAndFunctionPort {

    private final FindUserByRoleAndFunctionPort findUserByRolePort;
    
    @Override
    public List<UUID> execute(RoleEnum role, FunctionEnum function) {
        return findUserByRolePort.execute(role, function).stream().map(user -> user.getId()).toList();
    }
}
