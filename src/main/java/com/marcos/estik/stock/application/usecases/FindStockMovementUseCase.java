package com.marcos.estik.stock.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.stock.application.ports.in.FindStockMovementByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.application.view.StockMovementView;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindStockMovementUseCase implements FindStockMovementByIdPort {
    
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockFindItemByIdPort loadStockFindItemByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockFindDepartamentByIdPort;
    private final LoadStockFindFacilityByIdPort loadStockFindFacilityByIdPort;

    @Override
    public StockMovementView execute(UUID id) {
        StockMovement stockMovement = stockMovementRepositoryPort.findById(id);
        return new StockMovementView(
            stockMovement,
            loadStockFindItemByIdPort.execute(stockMovement.getItemId()),
            loadStockFindFacilityByIdPort.execute(stockMovement.getFacilitySourceId()),
            loadStockFindFacilityByIdPort.execute(stockMovement.getFacilityDestinationId()),
            loadStockFindDepartamentByIdPort.execute(stockMovement.getDepartamentSourceId()),
            loadStockFindDepartamentByIdPort.execute(stockMovement.getDepartamentDestinationId())
        );
    }
}
