package com.marcos.estik.stock.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.stock.application.command.CreateStockPurchaseCommand;
import com.marcos.estik.stock.application.command.EntryPurchaseCommand;
import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.application.ports.in.EntryPurchasePort;
import com.marcos.estik.stock.application.ports.out.LoadStockCreatePurchasePort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.application.view.StockDepartamentInfo;
import com.marcos.estik.stock.application.view.StockFacilityInfo;
import com.marcos.estik.stock.application.view.StockItemInfo;
import com.marcos.estik.stock.application.view.StockMovementView;
import com.marcos.estik.stock.application.view.StockPurchaseInfo;
import com.marcos.estik.stock.domain.StockBalance;
import com.marcos.estik.stock.domain.StockMovement;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EntryPurchaseUseCase implements EntryPurchasePort {
    
    private final LoadStockCreatePurchasePort loadStockCreatePurchasePort;
    private final LoadStockFindFacilityByIdPort loadStockFindFacilityByIdPort;
    private final LoadStockFindDepartamentByIdPort loadStockFindDepartamentByIdPort;
    private final LoadStockFindItemByIdPort loadStockFindItemByIdPort;
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;

    @Override
    @Transactional 
    public List<StockMovementView> execute(EntryPurchaseCommand command) {
        StockPurchaseInfo purchase = loadStockCreatePurchasePort.execute(
            new CreateStockPurchaseCommand(
                command.supplierId(),
                command.code(),
                command.file()
            )
        );

        List<StockMovement> items = command.items() 
            .stream()
            .map(item -> createIn(item, purchase))
            .toList();
        
        items.forEach(stockMovementRepositoryPort::save);
        return items.stream().map(item -> toView(item, purchase)).toList();
    }

    private StockMovement createIn(MovementCommand command, StockPurchaseInfo purchase) {
        StockDepartamentInfo departamentInfo = loadStockFindDepartamentByIdPort.execute(command.departamentId());
        StockFacilityInfo facilityInfo = loadStockFindFacilityByIdPort.execute(command.facilityId());
        StockItemInfo itemInfo = loadStockFindItemByIdPort.execute(command.itemId());
        StockBalance stockBalance;

        if (!stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            facilityInfo.id(), departamentInfo.id(), itemInfo.id())) {
                stockBalance = StockBalance.create(
                    facilityInfo.id(), 
                    departamentInfo.id(), 
                    itemInfo.id(), 
                    command.quantity()
                );
        } else {
            stockBalance = stockBalanceRepositoryPort.FindByFacilityIdAndDepartamentIdAndItemId(
                facilityInfo.id(), 
                departamentInfo.id(), 
                itemInfo.id()
            );

            stockBalance.entry(command.quantity());
        }

        stockBalanceRepositoryPort.save(stockBalance);
        return StockMovement.in(
            itemInfo.id(), 
            purchase.id(), 
            facilityInfo.id(), 
            departamentInfo.id(), 
            command.quantity(), 
            command.description()
        );
    }

    private StockMovementView toView(StockMovement stockMovement, StockPurchaseInfo purchaseInfo) {
        return new StockMovementView(
            stockMovement,
            purchaseInfo,
            loadStockFindItemByIdPort.execute(stockMovement.getItemId()),
            loadStockFindFacilityByIdPort.execute(stockMovement.getFacilitySourceId()),
            null,
            loadStockFindDepartamentByIdPort.execute(stockMovement.getDepartamentSourceId()),
            null
        );
    }
}
