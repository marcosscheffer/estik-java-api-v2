package com.marcos.estik.access.adapter.in.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.access.adapter.in.web.mapper.PermissionWebMapper;
import com.marcos.estik.access.adapter.in.web.request.CreatePermissionRequest;
import com.marcos.estik.access.adapter.in.web.response.PermissionResponse;
import com.marcos.estik.access.application.ports.in.CreatePermissionPort;
import com.marcos.estik.access.application.ports.in.DeletePermissionPort;
import com.marcos.estik.access.application.ports.in.FindPermissionsByRoleIdPort;
import com.marcos.estik.access.domain.Permission;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/access/permissions")
@RequiredArgsConstructor 
public class PermissionController {

    private final FindPermissionsByRoleIdPort findPermissionsByRoleIdPort;
    private final CreatePermissionPort createPermissionPort;
    private final DeletePermissionPort deletePermissionPort;
    private final PermissionWebMapper permissionWebMapper;

    @GetMapping("/role/{id}")
    public ResponseEntity<List<PermissionResponse>> getPermissions(
        @PathVariable UUID id
    ) {
        List<Permission> permissions = findPermissionsByRoleIdPort.execute(id);
        return ResponseEntity.ok(permissions.stream().map(permissionWebMapper::toResponse).toList());
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission(
        @RequestBody CreatePermissionRequest request
    ) {
        Permission permission = createPermissionPort.execute(request.roleId(), request.permission());
        return ResponseEntity.ok(permissionWebMapper.toResponse(permission));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deletePermission(
        @PathVariable UUID id
    ) {
        deletePermissionPort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
