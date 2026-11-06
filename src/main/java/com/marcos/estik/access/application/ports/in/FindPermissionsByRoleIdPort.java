package com.marcos.estik.access.application.ports.in;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.access.domain.Permission;

public interface FindPermissionsByRoleIdPort {
    
    List<Permission> execute(UUID id);

}
