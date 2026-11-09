package com.marcos.estik.access.application.ports.in;

import java.util.UUID;

public interface DeletePermissionPort {
    
    void execute(UUID id);

}
