package com.marcos.estik.stock.adapter.out.purchase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.purchase.application.port.in.FindPurchaseByIdPort;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.stock.application.ports.out.LoadStockFindPurchaseByidPort;
import com.marcos.estik.stock.application.view.StockPurchaseInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockFindPurchaseByIdAdapter implements LoadStockFindPurchaseByidPort {
    
    private final FindPurchaseByIdPort findPurchaseByIdPort;
    private final StockPurchaseMapper stockPurchaseMapper;

    @Override
    public StockPurchaseInfo execute(UUID id) {
        PurchaseView purchase = findPurchaseByIdPort.execute(id);
        return stockPurchaseMapper.toInfo(purchase);
    }
}
