package com.marcos.estik.stock.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindFacilityByIdPort;
import com.marcos.estik.stock.application.view.StockFacilityInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockFindFacilityByIdAdapter implements LoadStockFindFacilityByIdPort {
    
    private final FindFacilityByIdPort findFacilityByIdPort;
    private final StockFacilityMapper stockMovementFacilityMapper;

    @Override
    public StockFacilityInfo execute(UUID id) {
        return stockMovementFacilityMapper.toFacilityInfo(findFacilityByIdPort.execute(id));
    }
}
