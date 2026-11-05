package com.marcos.estik.computer.application.ports.in;

import com.marcos.estik.computer.application.command.SearchComputerHistoryCommand;
import com.marcos.estik.computer.application.view.ComputerHistoryView;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchComputerHistoryPort {
    PageResult<ComputerHistoryView> execute(SearchComputerHistoryCommand command);
}
