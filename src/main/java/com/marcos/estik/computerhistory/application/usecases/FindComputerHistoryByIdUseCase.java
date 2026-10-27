package com.marcos.estik.computerhistory.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computerhistory.application.ports.in.FindComputerHistoryByIdPort;
import com.marcos.estik.computerhistory.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computerhistory.application.ports.out.LoadComputerHistoryFindComputerByIdPort;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryView;
import com.marcos.estik.computerhistory.domain.ComputerHistory;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindComputerHistoryByIdUseCase implements FindComputerHistoryByIdPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final LoadComputerHistoryFindComputerByIdPort loadComputerHistoryFindComputerByIdPort;

    @Override
    public ComputerHistoryView execute(UUID id) {
        ComputerHistory computerHistory = computerHistoryRepositoryPort.findComputerHistoryById(id);
        return new ComputerHistoryView(
            computerHistory,
            loadComputerHistoryFindComputerByIdPort.execute(computerHistory.getComputerId())
        );
    }
}
