package com.marcos.estik.access.application.ports.in;

import java.util.List;

import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface FindPermissionByPermissionPort {
    
    List<Permission> execute(PermissionEnum permission);

}
