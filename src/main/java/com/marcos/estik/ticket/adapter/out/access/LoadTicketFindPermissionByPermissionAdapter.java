package com.marcos.estik.ticket.adapter.out.access;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.in.FindPermissionByPermissionPort;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindPermissionByPermissionPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketFindPermissionByPermissionAdapter implements LoadTicketFindPermissionByPermissionPort {
    
    private final FindPermissionByPermissionPort findPermissionByPermissionPort;

    @Override
    public Set<UUID> execute(List<PermissionEnum> permissions) {
        Set<UUID> permissionsId = new HashSet<>();

        permissions.stream().forEach(
            permission -> findPermissionByPermissionPort.execute(permission)
                .stream()
                .forEach(
                    p -> permissionsId.add(p.getRoleId())
                )
        );
        
        return permissionsId;        
    }
}
