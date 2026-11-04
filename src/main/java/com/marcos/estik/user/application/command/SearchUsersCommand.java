package com.marcos.estik.user.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.domain.user.RoleEnum;

public record SearchUsersCommand(
    String username,
    UUID facilityId,
    RoleEnum role,
    PageQuery page
) {
}
