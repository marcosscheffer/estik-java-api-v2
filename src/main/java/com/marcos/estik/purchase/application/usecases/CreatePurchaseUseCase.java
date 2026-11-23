package com.marcos.estik.purchase.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.purchase.application.command.CreatePurchaseCommand;
import com.marcos.estik.purchase.application.port.in.CreatePurchasePort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindSupplierByIdPort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseStoreFilePort;
import com.marcos.estik.purchase.application.port.out.PurchaseRepositoryPort;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;
import com.marcos.estik.purchase.application.view.PurchaseSupplierInfo;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.purchase.domain.Purchase;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreatePurchaseUseCase implements CreatePurchasePort {

    private final PurchaseRepositoryPort purchaseRepositoryPort;
    private final LoadPurchaseFindSupplierByIdPort loadPurchaseFindSupplierByIdPort;
    private final LoadPurchaseStoreFilePort loadPurchaseStoreFilePort;
    
    @Override
    @Transactional 
    public PurchaseView execute(CreatePurchaseCommand command) {
        PurchaseSupplierInfo supplierInfo = loadPurchaseFindSupplierByIdPort.execute(command.supplierId());
        PurchaseFileInfo file = loadPurchaseStoreFilePort.execute("/purchase", command.file());
        Purchase purchase = Purchase.create(
            file.id(),
            command.supplierId(), 
            command.code()
        );

        purchaseRepositoryPort.save(purchase);
        return new PurchaseView(
            purchase,
            file,
            supplierInfo
        );
    }
}
