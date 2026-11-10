package com.marcos.estik.user.application.port.out;

import java.util.UUID;

import com.marcos.estik.user.application.view.UserRoleInfo;


public interface LoadUserFindRoleByIdPort {
    
    UserRoleInfo execute(UUID id);

}
