package com.marcos.estik.supplier.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.supplier.application.port.in.CreateSupplierPort;
import com.marcos.estik.supplier.application.port.out.SupplierRepositoryPort;
import com.marcos.estik.supplier.domain.Supplier;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateSupplierUseCase implements CreateSupplierPort {

    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public Supplier execute(String name) {
        Supplier supplier = Supplier.create(name);
        supplierRepositoryPort.save(supplier);
        return supplier;
    }
}
