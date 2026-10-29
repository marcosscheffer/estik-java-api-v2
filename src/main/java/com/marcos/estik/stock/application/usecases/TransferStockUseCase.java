package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.stock.application.command.TransferCommand;
import com.marcos.estik.stock.application.ports.in.TransferStockPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
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
public class TransferStockUseCase implements TransferStockPort {

    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockFindFacilityByIdPort loadStockFindFacilityByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockFindDepartamentByIdPort;
    private final LoadStockFindItemByIdPort loadStockFindItemByIdPort;

    @Override
    @Transactional 
    public StockMovementView execute(TransferCommand command) {
        StockBalance stockBalanceSource = stockBalanceRepositoryPort.findById(command.stockBalanceId());
        StockFacilityInfo facilitySourceInfo = loadStockFindFacilityByIdPort.execute(stockBalanceSource.getFacilityId());
        StockDepartamentInfo departamentSourceInfo = loadStockFindDepartamentByIdPort.execute(stockBalanceSource.getDepartamentId());
        
        StockFacilityInfo facilityDestinationInfo = loadStockFindFacilityByIdPort.execute(command.facilityDestinationId());
        StockDepartamentInfo departamentDestinationInfo = loadStockFindDepartamentByIdPort.execute(command.departamentDestinationId());
        StockItemInfo itemInfo = loadStockFindItemByIdPort.execute(command.itemId());
        StockBalance stockBalanceDestination;


        if (stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            facilityDestinationInfo.id(),
            departamentDestinationInfo.id(),
            itemInfo.id()
            )
        ) {
            stockBalanceDestination = stockBalanceRepositoryPort.FindByFacilityIdAndDepartamentIdAndItemId(
                facilityDestinationInfo.id(),
                departamentDestinationInfo.id(),
                itemInfo.id()
            );
        } else {
            stockBalanceDestination = StockBalance.create(
                facilityDestinationInfo.id(),
                departamentDestinationInfo.id(),
                itemInfo.id(),
                0L
            );
        }

        stockBalanceSource.exit(command.quantity());
        stockBalanceDestination.entry(command.quantity());
        StockMovement stockMovement = StockMovement.transfer(
            itemInfo.id(), 
            facilitySourceInfo.id(), 
            departamentSourceInfo.id(), 
            facilityDestinationInfo.id(), 
            departamentDestinationInfo.id(), 
            command.quantity(), 
            command.description()
        );

        stockBalanceRepositoryPort.save(stockBalanceSource);
        stockBalanceRepositoryPort.save(stockBalanceDestination);
        stockMovementRepositoryPort.save(stockMovement);

        return new StockMovementView(
            stockMovement,
            itemInfo,
            facilitySourceInfo,
            facilityDestinationInfo,
            departamentSourceInfo,
            departamentDestinationInfo
        );
    }
}
