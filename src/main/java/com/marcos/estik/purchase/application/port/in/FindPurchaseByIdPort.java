package com.marcos.estik.purchase.application.port.in;

import java.util.UUID;

import com.marcos.estik.purchase.application.view.PurchaseView;

public interface FindPurchaseByIdPort {
    
    PurchaseView execute(UUID id);

}
