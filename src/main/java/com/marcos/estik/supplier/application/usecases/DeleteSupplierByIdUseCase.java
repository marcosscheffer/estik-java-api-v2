package com.marcos.estik.supplier.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.supplier.application.port.in.DeleteSupplierByIdPort;
import com.marcos.estik.supplier.application.port.out.SupplierRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class DeleteSupplierByIdUseCase implements DeleteSupplierByIdPort {
    
    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public void execute(UUID id) {
        supplierRepositoryPort.deleteById(id);
    }
    
}
