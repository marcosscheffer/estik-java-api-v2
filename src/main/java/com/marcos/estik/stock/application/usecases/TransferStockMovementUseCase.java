package com.marcos.estik.stock.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.stock.application.command.TransferMovementCommand;
import com.marcos.estik.stock.application.ports.in.TransferStockMovementPort;
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
public class TransferStockMovementUseCase implements TransferStockMovementPort {

    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final StockMovementRepositoryPort stockMovementRepositoryPort;
    private final LoadStockExistsDepartamentByIdPort loadStockExistsDepartamentByIdPort;
    private final LoadStockExistsItemByIdPort loadStockExistsItemByIdPort;
    private final LoadStockExistsFacilityByIdPort loadStockExistsFacilityByIdPort;
    
    @Override
    public StockMovement execute(UUID StockBalanceId, TransferMovementCommand command) {
        StockBalance stockBalanceSource = stockBalanceRepositoryPort.findById(StockBalanceId);
        StockBalance stockBalanceDestination;
        
        if(!loadStockExistsDepartamentByIdPort.existsById(command.departamentDestination())) {
            throw new EntityNotFoundException("departament invalid");
        }

        if(!loadStockExistsItemByIdPort.existsById(command.itemId())) {
            throw new EntityNotFoundException("item invalid");
        }

        if(!loadStockExistsFacilityByIdPort.existsById(command.facilityDestination())) {
            throw new EntityNotFoundException("facility invalid");
        }

        if(stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            command.facilityDestination(),
            command.departamentDestination(),
            command.itemId()
            )
        ) {
            stockBalanceDestination = stockBalanceRepositoryPort.FindByFacilityIdAndDepartamentIdAndItemId(
                command.facilityDestination(),
                command.departamentDestination(),
                command.itemId()
            );
        } else {
            stockBalanceDestination = StockBalance.create(
                command.facilityDestination(), 
                command.departamentDestination(),
                command.itemId(), 
                0L    
            );
        }

        stockBalanceSource.exit(command.quantity());
        stockBalanceDestination.entry(command.quantity());
        stockBalanceRepositoryPort.save(stockBalanceSource);
        stockBalanceRepositoryPort.save(stockBalanceDestination);

        StockMovement stockMovement = StockMovement.transfer(
            command.itemId(),
            stockBalanceSource.getFacilityId(),
            stockBalanceSource.getDepartamentId(),
            stockBalanceDestination.getFacilityId(),
            stockBalanceDestination.getDepartamentId(),
            command.quantity(),
            command.description()
        );

        return stockMovementRepositoryPort.save(stockMovement);
    }
}
