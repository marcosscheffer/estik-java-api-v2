package com.marcos.estik.auth.adapter.out.permission;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.in.FindPermissionsByRoleIdPort;
import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.auth.application.port.out.LoadUserFindPermissionByRoleIdPort;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserFindPermissionByRoleIdAdapter implements LoadUserFindPermissionByRoleIdPort {

    private final FindPermissionsByRoleIdPort findPermissionsByRoleIdPort;

    @Override
    public Set<PermissionEnum> execute(UUID id) {
        List<Permission> permissionsList = findPermissionsByRoleIdPort.execute(id);
        Set<PermissionEnum> permissions = EnumSet.noneOf(PermissionEnum.class);
        permissionsList.forEach(permission -> permissions.add(permission.getPermission()));
        return permissions;

    }
}
