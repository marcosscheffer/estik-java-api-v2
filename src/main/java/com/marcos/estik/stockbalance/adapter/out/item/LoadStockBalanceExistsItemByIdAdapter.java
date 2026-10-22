package com.marcos.estik.stockbalance.adapter.out.item;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.item.application.ports.in.ExistsItemByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsItemByIdPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class LoadStockBalanceExistsItemByIdAdapter implements LoadStockBalanceExistsItemByIdPort {
    
    private final ExistsItemByIdPort existsItemByIdPort;
    
    @Override
    public Boolean existsById(UUID id) {
        return existsItemByIdPort.execute(id);
    }
  
}
