package com.marcos.estik.user.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchUsersCommand(
    String username,
    UUID facilityId,
    PageQuery page
) {
}
