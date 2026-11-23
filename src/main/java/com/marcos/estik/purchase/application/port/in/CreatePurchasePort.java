package com.marcos.estik.purchase.application.port.in;

import com.marcos.estik.purchase.application.command.CreatePurchaseCommand;
import com.marcos.estik.purchase.application.view.PurchaseView;

public interface CreatePurchasePort {
    
    PurchaseView execute(CreatePurchaseCommand command);

}
