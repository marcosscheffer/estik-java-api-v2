package com.marcos.estik.access.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.CreatePermissionPort;
import com.marcos.estik.access.application.ports.out.PermissionRepositoryPort;
import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreatePermissionUseCase implements CreatePermissionPort {

    private final PermissionRepositoryPort permissionRepositoryPort;
    
    @Override
    public Permission execute(UUID roleId, PermissionEnum permissionName) {
        Permission permission = Permission.create(roleId, permissionName);
        return permissionRepositoryPort.save(permission);
    }
    
}
