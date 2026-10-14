package com.marcos.estik.computerhistory.application.ports.in;

import com.marcos.estik.computerhistory.application.command.SearchComputerHistoryCommand;
import com.marcos.estik.computerhistory.domain.ComputerHistory;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchComputerHistoryPort {
    PageResult<ComputerHistory> execute(SearchComputerHistoryCommand command);
}
