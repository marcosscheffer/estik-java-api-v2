package com.marcos.estik.stock.adapter.out.item;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.item.application.ports.in.FindItemByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockFindItemByIdPort;
import com.marcos.estik.stock.application.view.StockItemInfo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class LoadStockFindItemByIdAdapter implements LoadStockFindItemByIdPort {
    
    private final FindItemByIdPort findItemByIdPort;
    private final StockItemMapper stockMovementItemMapper;

    @Override
    public StockItemInfo execute(UUID id) {
        return stockMovementItemMapper.toItemInfo(findItemByIdPort.execute(id));
    }
  
}
