package com.marcos.estik.supplier.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.supplier.application.port.in.SearchSupplierPort;
import com.marcos.estik.supplier.application.port.out.SupplierRepositoryPort;
import com.marcos.estik.supplier.domain.Supplier;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSupplierUseCase implements SearchSupplierPort {
    
    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public PageResult<Supplier> execute(String name, PageQuery page) {
        return supplierRepositoryPort.search(name, page);
    }
    
}
