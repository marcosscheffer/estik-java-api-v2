package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.application.ports.in.AdjustStockPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindPurchaseByidPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.application.view.StockDepartamentInfo;
import com.marcos.estik.stock.application.view.StockFacilityInfo;
import com.marcos.estik.stock.application.view.StockItemInfo;
import com.marcos.estik.stock.application.view.StockMovementView;
import com.marcos.estik.stock.domain.StockBalance;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AdjustStockUseCase implements AdjustStockPort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockFindPurchaseByidPort loadStockFindPurchaseByidPort;
    private final LoadStockFindFacilityByIdPort loadStockBalanceFindFacilityByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockBalanceFindDepartamentByIdPort;
    private final LoadStockFindItemByIdPort loadStockBalanceFindItemByIdPort;
    
    @Override
    @Transactional
    public StockMovementView execute(MovementCommand command) {
        StockFacilityInfo facilityInfo = loadStockBalanceFindFacilityByIdPort.execute(command.facilityId());
        StockDepartamentInfo departamentInfo = loadStockBalanceFindDepartamentByIdPort.execute(command.departamentId());
        StockItemInfo itemInfo = loadStockBalanceFindItemByIdPort.execute(command.itemId());
        StockBalance stockBalance;

        if (stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            facilityInfo.id(),
            departamentInfo.id(),
            itemInfo.id()
            )
        ) {
            stockBalance = stockBalanceRepositoryPort.FindByFacilityIdAndDepartamentIdAndItemId(
                facilityInfo.id(),
                departamentInfo.id(),
                itemInfo.id()
            );
        } else {
            stockBalance = StockBalance.create(
                facilityInfo.id(),
                departamentInfo.id(),
                itemInfo.id(),
                0L
            );
        }

        stockBalance.adjust(command.quantity());

        StockMovement stockMovement = StockMovement.adjust(
            itemInfo.id(), 
            facilityInfo.id(), 
            departamentInfo.id(),
            command.quantity(), 
            command.description()
        );

        stockBalanceRepositoryPort.save(stockBalance);
        stockMovementRepositoryPort.save(stockMovement);

        return new StockMovementView(
            stockMovement,
            stockMovement.getPurchaseId() == null ? null : loadStockFindPurchaseByidPort.execute(stockMovement.getPurchaseId()),
            itemInfo,
            facilityInfo,
            null,
            departamentInfo,
            null
        );
    }
}
