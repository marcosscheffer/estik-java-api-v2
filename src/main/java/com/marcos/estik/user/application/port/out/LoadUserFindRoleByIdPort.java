package com.marcos.estik.user.application.port.out;

import java.util.UUID;

public interface LoadUserFindRoleByIdPort {
    
    String execute(UUID id);

}
