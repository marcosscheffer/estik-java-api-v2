package com.marcos.estik.user.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;

public record SearchUsersCommand(
    String username,
    UUID facilityId,
    FunctionEnum function,
    RoleEnum role,
    PageQuery page
) {
}
