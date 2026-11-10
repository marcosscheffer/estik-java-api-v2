package com.marcos.estik.user.application.view;

import com.marcos.estik.user.domain.User;

public record UserView(
    User user,
    UserRoleInfo role
) {
    
}
