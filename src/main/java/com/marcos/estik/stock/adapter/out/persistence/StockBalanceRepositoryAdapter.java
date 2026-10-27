package com.marcos.estik.stock.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.domain.StockBalance;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class StockBalanceRepositoryAdapter implements StockBalanceRepositoryPort {

    private final JpaStockBalanceRepository jpaStockBalanceRepository;
    private final StockBalancePersistenceMapper stockBalancePersistenceMapper;
    
    @Override
    public StockBalance save(StockBalance stockBalance) {
        StockBalanceEntity stockBalanceEntity = stockBalancePersistenceMapper.toEntity(stockBalance);
        StockBalanceEntity stockBalanceSaved = jpaStockBalanceRepository.save(stockBalanceEntity);
        return stockBalancePersistenceMapper.toDomain(stockBalanceSaved);
    }

    @Override
    public StockBalance findById(UUID id) {
        StockBalanceEntity stockBalance = jpaStockBalanceRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        return stockBalancePersistenceMapper.toDomain(stockBalance);
    }

    @Override
    public Boolean existsByFacilityIdAndDepartamentIdAndItemId(UUID facilityId, UUID departamentId, UUID itemId) {
        return jpaStockBalanceRepository.existsByFacilityIdAndDepartamentIdAndItemId(facilityId, departamentId, itemId);
    }

    @Override
    public PageResult<StockBalance> SearchStockBalance(
        UUID facilityId, 
        UUID departamentId, 
        UUID itemId,
        PageQuery page
    ) {
        Specification<StockBalanceEntity> spec = Specification.allOf(
            StockBalanceSpecification.departamentIs(departamentId),
            StockBalanceSpecification.faciltiyIs(facilityId),
            StockBalanceSpecification.itemIs(itemId)
        );

        Page<StockBalanceEntity> stockBalancePage = jpaStockBalanceRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size(), Sort.by(Sort.Direction.DESC, "updatedAt"))
        );

        return new PageResult<StockBalance>(
            stockBalancePage.getContent().stream().map(stockBalancePersistenceMapper::toDomain).toList(),
            stockBalancePage.getNumber(),
            stockBalancePage.getSize(),
            stockBalancePage.getTotalElements(),
            stockBalancePage.getTotalPages()
        );
    }

    @Override
    public StockBalance FindByFacilityIdAndDepartamentIdAndItemId(UUID facilityId, UUID departamentId, UUID itemId) {
        return jpaStockBalanceRepository.findByFacilityIdAndDepartamentIdAndItemId(facilityId, departamentId, itemId)
            .orElseThrow(EntityNotFoundException::new );
    }
}
