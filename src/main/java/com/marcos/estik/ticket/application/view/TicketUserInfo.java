package com.marcos.estik.ticket.application.view;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;

public record TicketUserInfo(
    UUID id,
    String username,
    RoleEnum role,
    FunctionEnum function
) {
    
}
