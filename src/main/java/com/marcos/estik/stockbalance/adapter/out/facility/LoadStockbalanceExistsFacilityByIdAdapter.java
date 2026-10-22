package com.marcos.estik.stockbalance.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockbalanceExistsFacilityByIdAdapter implements LoadStockBalanceExistsFacilityByIdPort {
    
    private final ExistsFacilityByIdPort existsFacilityByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsFacilityByIdPort.execute(id);
    }
    
}
