package com.marcos.estik.purchase.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.purchase.application.port.in.FindPurchaseByIdPort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindFileByIdPort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindSupplierByIdPort;
import com.marcos.estik.purchase.application.port.out.PurchaseRepositoryPort;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.purchase.domain.Purchase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
@Transactional(readOnly = true)
public class FindPurchaseByIdUseCase implements FindPurchaseByIdPort {

    private final PurchaseRepositoryPort purchaseRepositoryPort;
    private final LoadPurchaseFindSupplierByIdPort loadPurchaseFindSupplierByIdPort;
    private final LoadPurchaseFindFileByIdPort loadPurchaseFindFileByIdPort;

    @Override
    public PurchaseView execute(UUID id) {
        Purchase purchase = purchaseRepositoryPort.findById(id);
        PurchaseFileInfo file = loadPurchaseFindFileByIdPort.execute(purchase.getFileId());
        return new PurchaseView(
            purchase,
            file,
            loadPurchaseFindSupplierByIdPort.execute(purchase.getSupplierId())
        );
    }
}
