package com.marcos.estik.access.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.FindPermissionsByRoleIdPort;
import com.marcos.estik.access.application.ports.out.PermissionRepositoryPort;
import com.marcos.estik.access.domain.Permission;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindPermissionsByRoleIdUseCase implements FindPermissionsByRoleIdPort {
    
    private final PermissionRepositoryPort permissionRepositoryPort;
    
    @Override
    public List<Permission> execute(UUID id) {
        return permissionRepositoryPort.findByRoleId(id);
    }
    
}
