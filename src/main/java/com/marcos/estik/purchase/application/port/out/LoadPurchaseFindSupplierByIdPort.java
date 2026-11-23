package com.marcos.estik.purchase.application.port.out;

import java.util.UUID;

import com.marcos.estik.purchase.application.view.PurchaseSupplierInfo;

public interface LoadPurchaseFindSupplierByIdPort {
    
    PurchaseSupplierInfo execute(UUID id);

}
