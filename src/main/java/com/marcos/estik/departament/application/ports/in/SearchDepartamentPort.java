package com.marcos.estik.departament.application.ports.in;

import com.marcos.estik.departament.application.command.SearchDepartamentCommand;
import com.marcos.estik.departament.domain.Departament;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchDepartamentPort {
    
    PageResult<Departament> execute(SearchDepartamentCommand command);
}
