package com.marcos.estik.purchase.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {

    Page<PurchaseEntity> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    
}
