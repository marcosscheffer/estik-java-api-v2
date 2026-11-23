package com.marcos.estik.supplier.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, UUID> {

    Page<SupplierEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
