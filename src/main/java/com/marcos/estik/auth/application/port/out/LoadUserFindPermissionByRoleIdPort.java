package com.marcos.estik.auth.application.port.out;

import java.util.Set;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface LoadUserFindPermissionByRoleIdPort {
    Set<PermissionEnum> execute(UUID id);
}
