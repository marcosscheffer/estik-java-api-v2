package com.marcos.estik.specialization.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.specialization.domain.Specialization;

public interface SearchSpecializationPort {
    
    PageResult<Specialization> execute(String name, PageQuery page);

}
