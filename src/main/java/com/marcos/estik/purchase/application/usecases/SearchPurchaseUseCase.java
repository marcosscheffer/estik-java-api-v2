package com.marcos.estik.purchase.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.purchase.application.port.in.SearchPurchasePort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindFileByIdPort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindSupplierByIdPort;
import com.marcos.estik.purchase.application.port.out.PurchaseRepositoryPort;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.purchase.domain.Purchase;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchPurchaseUseCase implements SearchPurchasePort {

    private final PurchaseRepositoryPort purchaseRepositoryPort;
    private final LoadPurchaseFindSupplierByIdPort loadPurchaseFindSupplierByIdPort;
    private final LoadPurchaseFindFileByIdPort loadPurchaseFindFileByIdPort;

    @Override
    public PageResult<PurchaseView> execute(String code, PageQuery page) {
        PageResult<Purchase> purchases = purchaseRepositoryPort.search(code, page);
        return purchases.map(this::mapView);
    }

    private PurchaseView mapView(Purchase purchase) {
        return new PurchaseView(
            purchase,
            purchase.getFileId() == null ? null : loadPurchaseFindFileByIdPort.execute(purchase.getFileId()),
            loadPurchaseFindSupplierByIdPort.execute(purchase.getSupplierId())
        );
    }
}
