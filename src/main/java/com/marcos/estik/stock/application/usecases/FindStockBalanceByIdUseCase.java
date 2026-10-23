package com.marcos.estik.stock.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.stock.application.ports.in.FindStockBalanceByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.view.StockBalanceView;
import com.marcos.estik.stock.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindStockBalanceByIdUseCase implements FindStockBalanceByIdPort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final LoadStockFindFacilityByIdPort loadStockBalanceFindFacilityByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockBalanceFindDepartamentByIdPort;
    private final LoadStockFindItemByIdPort loadStockBalanceFindItemByIdPort;
    
    @Override
    public StockBalanceView execute(UUID id) {
        StockBalance stockBalance = stockBalanceRepositoryPort.findById(id);
        return new StockBalanceView(
            stockBalance,
            loadStockBalanceFindFacilityByIdPort.execute(stockBalance.getFacilityId()),
            loadStockBalanceFindDepartamentByIdPort.execute(stockBalance.getDepartamentId()),
            loadStockBalanceFindItemByIdPort.execute(stockBalance.getItemId())
        );
    }
}
