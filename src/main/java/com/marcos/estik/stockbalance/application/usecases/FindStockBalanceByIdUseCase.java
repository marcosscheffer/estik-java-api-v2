package com.marcos.estik.stockbalance.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.stockbalance.application.ports.in.FindStockBalanceByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stockbalance.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindStockBalanceByIdUseCase implements FindStockBalanceByIdPort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    
    @Override
    public StockBalance execute(UUID id) {
        return stockBalanceRepositoryPort.findById(id);
    }
    
}
