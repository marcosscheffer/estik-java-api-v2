package com.marcos.estik.auth.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRefreshtokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    
}
