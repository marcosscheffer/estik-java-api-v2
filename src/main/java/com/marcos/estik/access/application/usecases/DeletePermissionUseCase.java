package com.marcos.estik.access.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.DeletePermissionPort;
import com.marcos.estik.access.application.ports.out.PermissionRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeletePermissionUseCase implements DeletePermissionPort {
    
    private final PermissionRepositoryPort permissionRepositoryPort;

    @Override
    public void execute(UUID id) {
        permissionRepositoryPort.delete(id);
    }
    
}
