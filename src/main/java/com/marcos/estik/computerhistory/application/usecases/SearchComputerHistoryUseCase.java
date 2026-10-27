package com.marcos.estik.computerhistory.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.computerhistory.application.command.SearchComputerHistoryCommand;
import com.marcos.estik.computerhistory.application.ports.in.SearchComputerHistoryPort;
import com.marcos.estik.computerhistory.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computerhistory.application.ports.out.LoadComputerHistoryFindComputerByIdPort;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryView;
import com.marcos.estik.computerhistory.domain.ComputerHistory;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchComputerHistoryUseCase implements SearchComputerHistoryPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final LoadComputerHistoryFindComputerByIdPort loadComputerHistoryFindComputerByIdPort;

    @Override
    public PageResult<ComputerHistoryView> execute(SearchComputerHistoryCommand command) {
        PageResult<ComputerHistory> computerHistories = computerHistoryRepositoryPort.SearchComputerHistory(
            command.direction(),
            command.computerId(),
            command.departamentId(),
            command.page()
        );

        return computerHistories.map(computerHistory -> new ComputerHistoryView(
            computerHistory,
            loadComputerHistoryFindComputerByIdPort.execute(computerHistory.getComputerId())
        ));
    }
}
