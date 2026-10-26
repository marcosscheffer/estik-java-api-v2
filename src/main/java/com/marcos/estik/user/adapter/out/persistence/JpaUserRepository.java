package com.marcos.estik.user.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.domain.User;


public interface JpaUserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Page<UserEntity> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    List<User> findByRole(RoleEnum role);
}
