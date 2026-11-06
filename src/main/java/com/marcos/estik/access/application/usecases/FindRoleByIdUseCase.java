package com.marcos.estik.access.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.FindRoleByIdPort;
import com.marcos.estik.access.application.ports.out.RoleRepositoryPort;
import com.marcos.estik.access.domain.Role;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindRoleByIdUseCase implements FindRoleByIdPort {
    
    private final RoleRepositoryPort roleRepositoryPort;
    
    @Override
    public Role execute(UUID id) {
        return roleRepositoryPort.findById(id);
    }
    
}
