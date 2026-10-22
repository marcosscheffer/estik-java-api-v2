package com.marcos.estik.stock.adapter.out.item;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.item.application.ports.in.ExistsItemByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsItemByIdPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class LoadStockBalanceExistsItemByIdAdapter implements LoadStockExistsItemByIdPort {
    
    private final ExistsItemByIdPort existsItemByIdPort;
    
    @Override
    public Boolean existsById(UUID id) {
        return existsItemByIdPort.execute(id);
    }
  
}
