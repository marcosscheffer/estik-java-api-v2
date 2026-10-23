package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stock.application.ports.in.SearchStockBalancePort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.view.StockBalanceView;
import com.marcos.estik.stock.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchStockBalanceUseCase implements SearchStockBalancePort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final LoadStockFindFacilityByIdPort loadStockBalanceFindFacilityByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockBalanceFindDepartamentByIdPort;
    private final LoadStockFindItemByIdPort loadStockBalanceFindItemByIdPort;

    @Override
    public PageResult<StockBalanceView> execute(SearchStockBalanceCommand command) {
        PageResult<StockBalance> stockBalancePage = stockBalanceRepositoryPort.SearchStockBalance(
            command.facilityId(), 
            command.departamentId(), 
            command.itemId(), 
            command.page()
        );

        return stockBalancePage.map(stockBalance -> new StockBalanceView(
            stockBalance,
            loadStockBalanceFindFacilityByIdPort.execute(stockBalance.getFacilityId()),
            loadStockBalanceFindDepartamentByIdPort.execute(stockBalance.getDepartamentId()),
            loadStockBalanceFindItemByIdPort.execute(stockBalance.getItemId())
        ));
    }
}
