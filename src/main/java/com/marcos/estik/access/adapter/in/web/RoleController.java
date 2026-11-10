package com.marcos.estik.access.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.access.adapter.in.web.mapper.RoleWebMapper;
import com.marcos.estik.access.adapter.in.web.request.CreateRoleRequest;
import com.marcos.estik.access.adapter.in.web.response.RoleResponse;
import com.marcos.estik.access.application.ports.in.CreateRolePort;
import com.marcos.estik.access.application.ports.in.DeleteRolePort;
import com.marcos.estik.access.application.ports.in.FindRoleByIdPort;
import com.marcos.estik.access.application.ports.in.SearchAccessPort;
import com.marcos.estik.access.domain.Role;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/access/roles")
@RequiredArgsConstructor 
public class RoleController {
    
    private final SearchAccessPort searchAccessPort;
    private final CreateRolePort createRolePort;
    private final FindRoleByIdPort findRoleByIdPort;
    private final DeleteRolePort deleteRolePort;
    private final RoleWebMapper roleWebMapper;
    
    @GetMapping
    public ResponseEntity<PageResult<RoleResponse>> getAccesses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Role> roles = searchAccessPort.execute(
            new PageQuery(page, size)
        );

        return ResponseEntity.ok(
            roles.map(roleWebMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getAccess(
        @PathVariable UUID id
    ) {
        Role role = findRoleByIdPort.execute(id);

        return ResponseEntity.ok(
            roleWebMapper.toResponse(role)
        );
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
        @RequestBody CreateRoleRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Role role = createRolePort.execute(request.name());
        URI uri = uriBuilder
            .path("/access/roles/{id}")
            .buildAndExpand(role.getId())
            .toUri();

        return ResponseEntity.created(uri).body(roleWebMapper.toResponse(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(
        @PathVariable UUID id
    ) {
        deleteRolePort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
