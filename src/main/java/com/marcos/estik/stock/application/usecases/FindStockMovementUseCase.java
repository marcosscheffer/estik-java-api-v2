package com.marcos.estik.stock.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.stock.application.ports.in.FindStockMovementByIdPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindStockMovementUseCase implements FindStockMovementByIdPort {
    
    private final StockMovementRepositoryPort stockMovementRepositoryPort;

    @Override
    public StockMovement execute(UUID id) {
        return stockMovementRepositoryPort.findById(id);
    }
}
