package com.marcos.estik.stock.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.in.FindDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindDepartamentByIdPort;
import com.marcos.estik.stock.application.view.StockDepartamentInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockFindDepartamentByIdAdapter implements LoadStockFindDepartamentByIdPort {
    
    private final FindDepartamentByIdPort findDepartamentByIdPort;
    private final StockDepartamentMapper stockMovementDepartamentMapper;

    @Override
    public StockDepartamentInfo execute(UUID id) {
        return stockMovementDepartamentMapper.toDepartamentInfo(findDepartamentByIdPort.execute(id));
    }
    
}
