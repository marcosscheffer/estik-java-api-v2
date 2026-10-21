package com.marcos.estik.stockbalance.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stockbalance.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stockbalance.application.ports.in.SearchStockBalancePort;
import com.marcos.estik.stockbalance.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stockbalance.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchStockBalanceUseCase implements SearchStockBalancePort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;

    @Override
    public PageResult<StockBalance> execute(SearchStockBalanceCommand command) {
        return stockBalanceRepositoryPort.SearchStockBalance(
            command.facilityId(), 
            command.departamentId(), 
            command.itemId(), 
            command.page()
        );
    }
    
}
