package com.marcos.estik.computer.application.ports.in;

import com.marcos.estik.computer.application.command.SearchComputerCommand;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchComputerPort {
    
    PageResult<Computer> execute(SearchComputerCommand command);

}
