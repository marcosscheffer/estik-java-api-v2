package com.marcos.estik.user.application.port.in;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.user.application.view.UserView;

public interface FindUserByRolePort {

    List<UserView> execute(List<UUID> role);
    
}
