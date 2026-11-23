package com.marcos.estik.supplier.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.supplier.application.port.in.FindSupplierByIdPort;
import com.marcos.estik.supplier.application.port.out.SupplierRepositoryPort;
import com.marcos.estik.supplier.domain.Supplier;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSupplierByIdUseCase implements FindSupplierByIdPort {

    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public Supplier execute(UUID id) {
        return supplierRepositoryPort.findById(id);
    }
}
