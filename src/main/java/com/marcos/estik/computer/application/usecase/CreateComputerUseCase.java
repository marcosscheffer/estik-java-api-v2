package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.ports.in.CreateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerExistsDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerExistsFacilityByIdPort;
import com.marcos.estik.computer.domain.Computer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateComputerUseCase implements CreateComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerExistsDepartamentByIdPort loadComputerExistsDepartamentByIdPort;
    private final LoadComputerExistsFacilityByIdPort loadComputerExistsFacilityByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadComputerCreateComputerHistoryPort;

    @Override
    @Transactional
    public Computer execute(CreateComputerCommand command) {
        if(!loadComputerExistsDepartamentByIdPort.existsById(command.departamentId())) {
            throw new EntityNotFoundException("Departament not found");
        }
        if(!loadComputerExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("Facility not found");
        }

        Computer computer = Computer.create(
            command.name(),
            command.cpu(),
            command.ram(),
            command.os(),
            command.storageType(),
            command.storage(),
            command.facilityId(),
            command.departamentId()
        );
        
        computerRepositoryPort.save(computer);

        loadComputerCreateComputerHistoryPort.CreateComputerHistory(
            new LoadCreateComputerHistoryCommand(
                "Created",
                "SENT",
                computer.getId(),
                computer.getDepartamentId()
            )
        );

        return computer;
    }
}
