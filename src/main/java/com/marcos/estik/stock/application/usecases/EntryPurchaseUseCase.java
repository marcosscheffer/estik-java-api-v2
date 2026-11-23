package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.stock.application.command.EntryPurchaseCommand;
import com.marcos.estik.stock.application.ports.in.EntryPurchasePort;
import com.marcos.estik.stock.application.ports.out.LoadStockCreatePurchasePort;
import com.marcos.estik.stock.application.view.StockMovementView;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EntryPurchaseUseCase implements EntryPurchasePort {
    
    private final LoadStockCreatePurchasePort loadStockCreatePurchasePort;

    @Override
    public StockMovementView execute(EntryPurchaseCommand command) {
        loadStockCreatePurchasePort.execute(null);
    }
    
}
