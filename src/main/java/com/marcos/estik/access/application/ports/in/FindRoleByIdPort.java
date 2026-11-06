package com.marcos.estik.access.application.ports.in;

import java.util.UUID;

import com.marcos.estik.access.domain.Role;

public interface FindRoleByIdPort {
    
    Role execute(UUID id);

}
