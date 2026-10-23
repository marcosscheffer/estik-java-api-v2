package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockMovementCommand;
import com.marcos.estik.stock.application.ports.in.SearchStockMovementPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.application.view.StockMovementView;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchStockMovementUseCase implements SearchStockMovementPort {
    
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockFindItemByIdPort loadStockFindItemByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockFindDepartamentByIdPort;
    private final LoadStockFindFacilityByIdPort loadStockFindFacilityByIdPort;
    
    @Override
    public PageResult<StockMovementView> execute(SearchStockMovementCommand command) {
        PageResult<StockMovement> stockMovements = stockMovementRepositoryPort.searchStockMovement(
            command.facilityId(), 
            command.departamentId(), 
            command.type(),
            command.page()
        );



        return stockMovements.map(stockMovement -> new StockMovementView(
            stockMovement,
            loadStockFindItemByIdPort.execute(stockMovement.getItemId()),
            loadStockFindFacilityByIdPort.execute(stockMovement.getFacilitySourceId()),
            stockMovement.getFacilityDestinationId() == null ? null : loadStockFindFacilityByIdPort.execute(stockMovement.getFacilityDestinationId()),
            loadStockFindDepartamentByIdPort.execute(stockMovement.getDepartamentSourceId()),
            stockMovement.getDepartamentDestinationId() == null ? null : loadStockFindDepartamentByIdPort.execute(stockMovement.getDepartamentDestinationId())
        ));
    }
}
