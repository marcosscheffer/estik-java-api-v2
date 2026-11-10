package com.marcos.estik.ticket.application.ports.out;

import java.util.Set;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface LoadTicketFindPermissionByPermissionPort {
    
    Set<UUID> execute(PermissionEnum permission);
}
