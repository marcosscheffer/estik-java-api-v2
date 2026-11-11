package com.marcos.estik.user.application.port.out;

import java.util.UUID;

import com.marcos.estik.user.application.view.UserSpecializationInfo;


public interface LoadUserFindSpecializationByIdPort {
    
    UserSpecializationInfo execute(UUID id);

}
