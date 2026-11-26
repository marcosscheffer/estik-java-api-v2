package com.marcos.estik.supplier.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.supplier.domain.Supplier;

public interface SupplierRepositoryPort {
    
    PageResult<Supplier> search(String name, PageQuery page);

    Supplier findById(UUID id);

    Supplier save(Supplier supplier);

    void deleteById(UUID id);

}
