package com.marcos.estik.purchase.adapter.out.supplier;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindSupplierByIdPort;
import com.marcos.estik.purchase.application.view.PurchaseSupplierInfo;
import com.marcos.estik.supplier.application.port.in.FindSupplierByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadPurchaseFindSupplierByIdAdapter implements LoadPurchaseFindSupplierByIdPort {

    private final FindSupplierByIdPort findSupplierByIdPort;
    private final PurchaseSupplierMapper purchaseSupplierMapper;
    
    @Override
    public PurchaseSupplierInfo execute(UUID id) {
        return purchaseSupplierMapper.toInfo(findSupplierByIdPort.execute(id));
    }
    
}
