package com.marcos.estik.access.application.ports.out;

import java.util.UUID;

import com.marcos.estik.access.domain.Role;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface RoleRepositoryPort {
    
    Role findById(UUID id);

    PageResult<Role> searchAccess(PageQuery page);

    void delete(UUID id);

    Role save(Role role);

}
