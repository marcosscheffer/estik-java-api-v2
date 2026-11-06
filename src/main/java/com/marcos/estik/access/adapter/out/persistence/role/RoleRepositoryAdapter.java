package com.marcos.estik.access.adapter.out.persistence.role;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.out.RoleRepositoryPort;
import com.marcos.estik.access.domain.Role;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;
    private final RolePersistenceMapper rolePersistenceMapper;

    @Override
    public Role findById(UUID id) {
        RoleEntity role = jpaRoleRepository.findById(id)
            .orElseThrow(
                EntityNotFoundException::new 
            );
        return rolePersistenceMapper.toDomain(role);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = rolePersistenceMapper.toEntity(role);
        RoleEntity roleEntitySaved = jpaRoleRepository.save(roleEntity);
        return rolePersistenceMapper.toDomain(roleEntitySaved);
    }
}
