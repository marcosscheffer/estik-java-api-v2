package com.marcos.estik.access.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.FindPermissionByPermissionPort;
import com.marcos.estik.access.application.ports.out.PermissionRepositoryPort;
import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindPermissionByPermissionUseCase implements FindPermissionByPermissionPort {

    private final PermissionRepositoryPort permissionRepositoryPort;

    @Override
    public List<Permission> execute(PermissionEnum permission) {
        return permissionRepositoryPort.findByPermission(permission);
    }
    
}
