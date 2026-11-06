package com.marcos.estik.access.application.ports.out;

import java.util.UUID;

import com.marcos.estik.access.domain.Role;

public interface RoleRepositoryPort {
    
    Role findById(UUID id);

    Role save(Role role);

}
