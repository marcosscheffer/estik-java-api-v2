package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.application.ports.in.UpdateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindFacilityByIdPort;
import com.marcos.estik.computer.application.view.ComputerView;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.computer.domain.ComputerHistory;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateComputerUseCase implements UpdateComputerPort {
    
    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final LoadComputerFindDepartamentByIdPort loadComputerFindDepartamentByIdPort;
    private final LoadComputerFindFacilityByIdPort loadComputerFindFacilityByIdPort;
    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    @Transactional
    public ComputerView execute(UpdateComputerCommand command, UUID computerId) {
        Computer computer = computerRepositoryPort.findComputerById(computerId);

        computer.updateSpecs(
            command.cpu(), 
            command.ram(), 
            command.os(), 
            command.storageType(), 
            command.storage()
        );

        computerRepositoryPort.save(computer);

        ComputerHistory history = ComputerHistory.create(
            command.serviceDescription(),
            command.direction(),
            computer.getId(), 
            computer.getDepartamentId()
        );

        computerHistoryRepositoryPort.save(history);

        return new ComputerView(
            computer,
            loadComputerFindFacilityByIdPort.execute(computer.getFacilityId()),
            loadComputerFindDepartamentByIdPort.execute(computer.getDepartamentId())
        );
    }
}
