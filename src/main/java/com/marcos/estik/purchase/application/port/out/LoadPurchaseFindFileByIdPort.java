package com.marcos.estik.purchase.application.port.out;

import java.util.UUID;

import com.marcos.estik.purchase.application.view.PurchaseFileInfo;

public interface LoadPurchaseFindFileByIdPort {
    
    PurchaseFileInfo execute(UUID id);

}
