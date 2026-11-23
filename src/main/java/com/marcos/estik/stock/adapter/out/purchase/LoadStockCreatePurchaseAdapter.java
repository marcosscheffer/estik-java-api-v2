package com.marcos.estik.stock.adapter.out.purchase;

import org.springframework.stereotype.Component;

import com.marcos.estik.purchase.application.command.CreatePurchaseCommand;
import com.marcos.estik.purchase.application.port.in.CreatePurchasePort;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.stock.application.command.CreateStockPurchaseCommand;
import com.marcos.estik.stock.application.ports.out.LoadStockCreatePurchasePort;
import com.marcos.estik.stock.application.view.StockPurchaseInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockCreatePurchaseAdapter implements LoadStockCreatePurchasePort {
    
    private final CreatePurchasePort createPurchasePort;
    private final StockPurchaseMapper stockPurchaseMapper;
    
    @Override
    public StockPurchaseInfo execute(CreateStockPurchaseCommand command) {
        PurchaseView purchase = createPurchasePort.execute(new CreatePurchaseCommand(
            command.supplierId(),
            command.code(),
            command.file()
        ));

        return stockPurchaseMapper.toInfo(purchase);
    }
}
