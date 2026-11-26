package com.marcos.estik.purchase.application.port.out;

import java.util.UUID;

import com.marcos.estik.purchase.domain.Purchase;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface PurchaseRepositoryPort {
    
    Purchase findById(UUID id);

    PageResult<Purchase> search(String code, PageQuery page);

    Purchase save(Purchase purchase);

    void deleteById(UUID id);

}
