package com.marcos.estik.access.adapter.out.persistence.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {

}
