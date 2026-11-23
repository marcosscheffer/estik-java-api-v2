package com.marcos.estik.supplier.application.port.in;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.supplier.domain.Supplier;

public interface SearchSupplierPort {

    PageResult<Supplier> execute(String name, PageQuery page);
    
}
