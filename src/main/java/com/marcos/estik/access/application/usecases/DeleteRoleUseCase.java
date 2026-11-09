package com.marcos.estik.access.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.DeleteRolePort;
import com.marcos.estik.access.application.ports.out.RoleRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteRoleUseCase implements DeleteRolePort {
    
    private final RoleRepositoryPort roleRepositoryPort;
    
    @Override
    public void execute(UUID id) {
        roleRepositoryPort.delete(id);
    }
    
}
