package com.marcos.estik.purchase.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.purchase.application.port.out.PurchaseRepositoryPort;
import com.marcos.estik.purchase.domain.Purchase;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class PurchaseRepositoryAdapter implements PurchaseRepositoryPort {
    
    private final JpaPurchaseRepository jpaPurchaseRepository;
    private final PurchasePersistenceMapper purchasePersistenceMapper;
    
    @Override
    public Purchase findById(UUID id) {
        return purchasePersistenceMapper.toDomain(
            jpaPurchaseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new 
            )
        );
    }

    @Override
    public PageResult<Purchase> search(String code, PageQuery page) {
        Page<PurchaseEntity> purchases = jpaPurchaseRepository.findByCodeContainingIgnoreCase(code, PageRequest.of(page.page(), page.size()));
        return new PageResult<Purchase>(
            purchases.getContent().stream().map(purchasePersistenceMapper::toDomain).toList(),
            purchases.getNumber(),
            purchases.getSize(),
            purchases.getTotalElements(),
            purchases.getTotalPages()
        );
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity purchaseEntity = purchasePersistenceMapper.toEntity(purchase);
        PurchaseEntity purchaseEntitySaved = jpaPurchaseRepository.save(purchaseEntity);
        return purchasePersistenceMapper.toDomain(purchaseEntitySaved);
    }
    
}
