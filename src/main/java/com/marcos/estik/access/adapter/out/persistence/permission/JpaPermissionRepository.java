package com.marcos.estik.access.adapter.out.persistence.permission;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface JpaPermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    List<PermissionEntity> findByRoleId(UUID id);

    List<PermissionEntity> findByPermission(PermissionEnum permission);
    
}
