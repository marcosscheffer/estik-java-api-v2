package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.ports.in.FindComputerHistoryByIdPort;
import com.marcos.estik.computer.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.view.ComputerHistoryView;
import com.marcos.estik.computer.application.view.ComputerInfo;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.computer.domain.ComputerHistory;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindComputerHistoryByIdUseCase implements FindComputerHistoryByIdPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    public ComputerHistoryView execute(UUID id) {
        ComputerHistory computerHistory = computerHistoryRepositoryPort.findComputerHistoryById(id);
        Computer computer = computerRepositoryPort.findComputerById(computerHistory.getComputerId());
        return new ComputerHistoryView(
            computerHistory,
            new ComputerInfo(computer.getId(), computer.getName(), computer.getUpdatedAt())
        );
    }
}
