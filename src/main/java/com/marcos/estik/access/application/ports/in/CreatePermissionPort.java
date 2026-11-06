package com.marcos.estik.access.application.ports.in;

import java.util.UUID;

import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface CreatePermissionPort {

    Permission execute(UUID roleId, PermissionEnum permission);

} 

    

