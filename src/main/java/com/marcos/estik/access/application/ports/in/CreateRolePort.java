package com.marcos.estik.access.application.ports.in;

import com.marcos.estik.access.domain.Role;

public interface CreateRolePort {

    Role execute(String name);
    
}
