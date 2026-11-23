package com.marcos.estik.purchase.application.view;

import com.marcos.estik.purchase.domain.Purchase;

public record PurchaseView(
    Purchase purchase,
    PurchaseFileInfo file,
    PurchaseSupplierInfo supplier
) {
    
}
