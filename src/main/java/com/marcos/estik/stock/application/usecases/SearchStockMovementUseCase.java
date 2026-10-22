package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockMovementCommand;
import com.marcos.estik.stock.application.ports.in.SearchStockMovementPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchStockMovementUseCase implements SearchStockMovementPort {
    
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    
    @Override
    public PageResult<StockMovement> execute(SearchStockMovementCommand command) {
        return stockMovementRepositoryPort.searchStockMovement(
            command.facilityId(), 
            command.departamentId(), 
            command.type(),
            command.page()
        );
    }
}
