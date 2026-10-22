package com.marcos.estik.stockbalance.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.stockbalance.application.command.CreateStockBalanceCommand;
import com.marcos.estik.stockbalance.application.ports.in.CreateStockBalancePort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsDepartamentByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsFacilityByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsItemByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stockbalance.domain.StockBalance;
import com.marcos.estik.stockbalance.domain.exception.ItemAlreadyExistsException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateStockBalanceUseCase implements CreateStockBalancePort {
    
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;
    private final LoadStockBalanceExistsDepartamentByIdPort loadStockBalanceExistsDepartamentByIdPort;
    private final LoadStockBalanceExistsFacilityByIdPort loadStockBalanceExistsFacilityByIdPort;
    private final LoadStockBalanceExistsItemByIdPort loadStockBalanceExistsItemByIdPort;

    @Override
    public StockBalance execute(CreateStockBalanceCommand command) {
        if(stockBalanceRepositoryPort.existsByFacilityIdAndDepartamentIdAndItemId(
            command.facilityId(), 
            command.departamentId(), 
            command.itemId())
        ) {
            throw new ItemAlreadyExistsException("item already exists in this facility or departament");
        }

        if(! loadStockBalanceExistsDepartamentByIdPort.existsById(command.departamentId())) {
            throw new EntityNotFoundException("departament invalid");
        }

        if(!loadStockBalanceExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("facility invalid");
        }

        if(!loadStockBalanceExistsItemByIdPort.existsById(command.itemId())) {
            throw new EntityNotFoundException("item invalid");
        }

        StockBalance stockBalance = StockBalance.create(
            command.facilityId(),
            command.departamentId(),
            command.itemId(),
            command.quantity()
        );

        return stockBalanceRepositoryPort.save(stockBalance);
    }
}
