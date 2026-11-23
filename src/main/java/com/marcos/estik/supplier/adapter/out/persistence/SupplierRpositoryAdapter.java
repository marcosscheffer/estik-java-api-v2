package com.marcos.estik.supplier.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.supplier.application.port.out.SupplierRepositoryPort;
import com.marcos.estik.supplier.domain.Supplier;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SupplierRpositoryAdapter implements SupplierRepositoryPort {

    private final JpaSupplierRepository jpaSupplierRepository;
    private final SupplierPersistenceMapper supplierPersistenceMapper;
    
    @Override
    public PageResult<Supplier> search(String name, PageQuery page) {
        Page<SupplierEntity> suppliers = jpaSupplierRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page.page(), page.size()));
        return new PageResult<Supplier>(
            suppliers.stream().map(supplierPersistenceMapper::toDomain).toList(),
            suppliers.getNumber(),
            suppliers.getSize(),
            suppliers.getTotalElements(),
            suppliers.getTotalPages()
        );
    }

    @Override
    public Supplier findById(UUID id) {
        return supplierPersistenceMapper.toDomain(
            jpaSupplierRepository.findById(id).orElseThrow(
                EntityNotFoundException::new 
            )
        );
    }

    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity supplierEntity = supplierPersistenceMapper.toEntity(supplier);
        SupplierEntity supplierEntitySaved = jpaSupplierRepository.save(supplierEntity);
        return supplierPersistenceMapper.toDomain(supplierEntitySaved);
    }
}
