package com.marcos.estik.access.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface PermissionRepositoryPort {
    
    
   List<Permission> findByRoleId(UUID id);

   Permission save(Permission permission);

   void delete(UUID id);

   Permission findById(UUID id);

   List<Permission> findByPermission(PermissionEnum permission);

}
