package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.application.ports.in.UpdateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateComputerUseCase implements UpdateComputerPort {
    
    private final LoadComputerCreateComputerHistoryPort loadCreateComputerHistoryPort;
    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    @Transactional
    public Computer execute(UpdateComputerCommand command, UUID computerId) {
        Computer computer = computerRepositoryPort.findComputerById(computerId);

        computer.updateSpecs(
            command.cpu(), 
            command.ram(), 
            command.os(), 
            command.storageType(), 
            command.storage()
        );

        loadCreateComputerHistoryPort.CreateComputerHistory(
            new LoadCreateComputerHistoryCommand(
                command.serviceDescription(),
                command.direction(),
                computerId,
                computer.getDepartamentId()
            )
        );

        return computerRepositoryPort.save(computer);
    }
    
}
