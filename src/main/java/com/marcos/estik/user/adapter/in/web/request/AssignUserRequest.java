package com.marcos.estik.user.adapter.in.web.request;

import com.marcos.estik.shared.domain.user.RoleEnum;

public record AssignUserRequest(
    RoleEnum role
) {
    
}
