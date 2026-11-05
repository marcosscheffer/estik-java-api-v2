package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.command.SearchComputerHistoryCommand;
import com.marcos.estik.computer.application.ports.in.SearchComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.view.ComputerHistoryView;
import com.marcos.estik.computer.application.view.ComputerInfo;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.computer.domain.ComputerHistory;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchComputerHistoryUseCase implements SearchComputerHistoryPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    public PageResult<ComputerHistoryView> execute(SearchComputerHistoryCommand command) {
        PageResult<ComputerHistory> computerHistories = computerHistoryRepositoryPort.SearchComputerHistory(
            command.direction(),
            command.computerId(),
            command.departamentId(),
            command.page()
        );

        return computerHistories.map(
            computerHistory -> {
                Computer computer = computerRepositoryPort.findComputerById(computerHistory.getComputerId());
                return new ComputerHistoryView(
                    computerHistory,
                    new ComputerInfo(computer.getId(), computer.getName(), computer.getUpdatedAt())
                );
            }
        );
    }
}
