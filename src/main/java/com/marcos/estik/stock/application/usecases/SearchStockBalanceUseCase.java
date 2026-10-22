package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stock.application.ports.in.SearchStockBalancePort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.domain.StockBalance;

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
