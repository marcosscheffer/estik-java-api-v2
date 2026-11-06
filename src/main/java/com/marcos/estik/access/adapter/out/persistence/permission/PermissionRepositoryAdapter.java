package com.marcos.estik.access.adapter.out.persistence.permission;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.out.PermissionRepositoryPort;
import com.marcos.estik.access.domain.Permission;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class PermissionRepositoryAdapter implements PermissionRepositoryPort {
    
    private final JpaPermissionRepository jpaPermissionRepository;
    private final PermissionPersistenceMapper permissionPersistenceMapper;
    
    @Override
    public List<Permission> findByRoleId(UUID id) {
        List<PermissionEntity> permissionEntities = jpaPermissionRepository.findByRoleId(id);
        return permissionEntities.stream().map(permissionPersistenceMapper::toDomain).toList();
    }

    @Override
    public Permission save(Permission permission) {
        PermissionEntity permissionEntity = permissionPersistenceMapper.toEntity(permission);
        PermissionEntity permissionEntitySaved = jpaPermissionRepository.save(permissionEntity);
        return permissionPersistenceMapper.toDomain(permissionEntitySaved);
    }

    @Override
    public Permission findById(UUID id) {
        PermissionEntity permissionEntity = jpaPermissionRepository.findById(id)
            .orElseThrow(
                EntityNotFoundException::new 
            );
        return permissionPersistenceMapper.toDomain(permissionEntity);
    }
}
