package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.application.ports.in.MoveComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyDepartamentExistsByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyFacilityExistsByIdPort;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class MoveComputerUseCase implements MoveComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerVerifyFacilityExistsByIdPort loadComputerVerifyFacilityExistsByIdPort;
    private final LoadComputerVerifyDepartamentExistsByIdPort loadComputerVerifyDepartamentExistsByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadComputerCreateComputerHistoryPort;

    @Override
    public Computer execute(UUID computerId, MoveComputerCommand command) {
        loadComputerVerifyFacilityExistsByIdPort.existsById(command.facilityId());
        loadComputerVerifyDepartamentExistsByIdPort.existsById(command.departamentId());

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
