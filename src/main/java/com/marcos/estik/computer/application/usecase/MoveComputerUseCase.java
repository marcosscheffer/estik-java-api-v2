package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.application.ports.in.MoveComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerExistsDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerExistsFacilityByIdPort;
import com.marcos.estik.computer.domain.Computer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class MoveComputerUseCase implements MoveComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerExistsDepartamentByIdPort loadComputerExistsDepartamentByIdPort;
    private final LoadComputerExistsFacilityByIdPort loadComputerExistsFacilityByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadComputerCreateComputerHistoryPort;

    @Override
    public Computer execute(UUID computerId, MoveComputerCommand command) {
        if(!loadComputerExistsDepartamentByIdPort.existsById(command.departamentId())) {
            throw new EntityNotFoundException("Departament not found");
        }
        if(!loadComputerExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("Facility not found");
        }

        Computer computer = computerRepositoryPort.findComputerById(computerId);
        computer.moveTo(command.facilityId(), command.departamentId());

        loadComputerCreateComputerHistoryPort.CreateComputerHistory(
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
