package com.marcos.estik.stock.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.domain.StockMovement;
import com.marcos.estik.stock.domain.TypeMovementEnum;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class StockMovementRepositoryAdapter implements StockMovementRepositoryPort {

    private final JpaStockMovementRepository jpaStockMovementRepository;
    private final StockMovementPersistenceMapper stockMovementPersistenceMapper;

    @Override
    public StockMovement save(StockMovement stockMovement) {
        StockMovementEntity stockMovementEntity = stockMovementPersistenceMapper.toEntity(stockMovement);
        StockMovementEntity stockMovementSaved = jpaStockMovementRepository.save(stockMovementEntity);
        return stockMovementPersistenceMapper.toDomain(stockMovementSaved);
    }

    @Override
    public StockMovement findById(UUID id) {
        StockMovementEntity stockMovement = jpaStockMovementRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new );

        return stockMovementPersistenceMapper.toDomain(stockMovement);
    }

    @Override
    public PageResult<StockMovement> searchStockMovement(
        UUID facilityId, 
        UUID departamentId, 
        TypeMovementEnum type,
        PageQuery page
        ) {
        Specification<StockMovementEntity> spec = Specification.allOf(
            StockMovementSpecification.departamentIs(departamentId),
            StockMovementSpecification.facilityIs(facilityId),
            StockMovementSpecification.typeIs(type)
        );

        Page<StockMovementEntity> stockMovementPage = jpaStockMovementRepository.findAll(
            spec,
            PageRequest.of(page.page(), page.size(), Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return new PageResult<StockMovement>(
            stockMovementPage.getContent().stream().map(stockMovementPersistenceMapper::toDomain).toList(),
            stockMovementPage.getNumber(),
            stockMovementPage.getSize(),
            stockMovementPage.getTotalElements(),
            stockMovementPage.getTotalPages()
        );
    }
}
