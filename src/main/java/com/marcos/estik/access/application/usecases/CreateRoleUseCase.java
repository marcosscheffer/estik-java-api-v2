package com.marcos.estik.access.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.CreateRolePort;
import com.marcos.estik.access.application.ports.out.RoleRepositoryPort;
import com.marcos.estik.access.domain.Role;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateRoleUseCase implements CreateRolePort {

    private final RoleRepositoryPort roleRepositoryPort;
    
    @Override
    public Role execute(String name) {
        Role role = Role.create(name);
        return roleRepositoryPort.save(role);
    }
    
}
