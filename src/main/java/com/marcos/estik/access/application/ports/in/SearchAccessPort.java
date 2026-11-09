package com.marcos.estik.access.application.ports.in;

import com.marcos.estik.access.domain.Role;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchAccessPort {
    
    PageResult<Role> execute(PageQuery page);

}
