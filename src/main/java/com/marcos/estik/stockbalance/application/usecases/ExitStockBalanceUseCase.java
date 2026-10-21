package com.marcos.estik.stockbalance.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.stockbalance.application.ports.in.ExitStockBalancePort;
import com.marcos.estik.stockbalance.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stockbalance.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExitStockBalanceUseCase implements ExitStockBalancePort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    
    @Override
    public StockBalance execute(UUID id, Long quantity) {
        StockBalance stockBalance = stockBalanceRepositoryPort.findById(id);
        stockBalance.exit(quantity);
        return stockBalanceRepositoryPort.save(stockBalance);
    }
    
}
