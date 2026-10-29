package com.marcos.estik.notification.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;

public interface LoadNotificationFindUserByRoleAndFunctionPort {
    
    List<UUID> execute(RoleEnum role, FunctionEnum function);

}
