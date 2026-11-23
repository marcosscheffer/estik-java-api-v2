package com.marcos.estik.purchase.application.port.in;

import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchPurchasePort {
    
    PageResult<PurchaseView> execute(String code, PageQuery page);

}
