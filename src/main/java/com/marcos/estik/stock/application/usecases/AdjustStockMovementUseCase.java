package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.application.ports.in.AdjustStockMovementPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsItemByIdPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.ports.out.StockMovementRepositoryPort;
import com.marcos.estik.stock.domain.StockBalance;
import com.marcos.estik.stock.domain.StockMovement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class AdjustStockMovementUseCase implements AdjustStockMovementPort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockExistsDepartamentByIdPort loadStockExistsDepartamentByIdPort;
    private final LoadStockExistsItemByIdPort loadStockExistsItemByIdPort;
    private final LoadStockExistsFacilityByIdPort loadStockExistsFacilityByIdPort;
    
    @Override
    @Transactional 
    public StockMovement execute(MovementCommand command) {
        StockBalance stockBalance;

        if(!loadStockExistsDepartamentByIdPort.existsById(command.departamentId())) {
            throw new EntityNotFoundException("departament invalid");
        }

        if(!loadStockExistsItemByIdPort.existsById(command.itemId())) {
            throw new EntityNotFoundException("item invalid");
        }

        if(!loadStockExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("facility invalid");
        }

        if (stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            command.facilityId(), 
            command.departamentId(), 
            command.itemId()
            )
        ) {
            stockBalance = stockBalanceRepositoryPort.FindByFacilityIdAndDepartamentIdAndItemId(
                command.facilityId(), 
                command.departamentId(), 
                command.itemId()
            );
        } else {
            stockBalance = StockBalance.create(
                command.facilityId(), 
                command.departamentId(), 
                command.itemId(), 
                0L
            );
        }

        stockBalance.adjust(command.quantity());
        stockBalanceRepositoryPort.save(stockBalance);

        StockMovement stockMovement = StockMovement.adjust(
            command.itemId(), 
            command.facilityId(), 
            command.departamentId(), 
            command.quantity(), 
            command.description() 
        );
        return stockMovementRepositoryPort.save(stockMovement);
    }
}
