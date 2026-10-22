package com.marcos.estik.stock.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockbalanceExistsFacilityByIdAdapter implements LoadStockExistsFacilityByIdPort {
    
    private final ExistsFacilityByIdPort existsFacilityByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsFacilityByIdPort.execute(id);
    }
    
}
