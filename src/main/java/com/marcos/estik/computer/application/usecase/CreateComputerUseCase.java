package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.ports.in.CreateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyDepartamentExistsByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyFacilityExistsByIdPort;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateComputerUseCase implements CreateComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerVerifyFacilityExistsByIdPort loadVerifyFacilityExistsByIdPort;
    private final LoadComputerVerifyDepartamentExistsByIdPort loadVerifyDepartamentExistsByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadCreateComputerHistoryPort;

    @Override
    @Transactional
    public Computer execute(CreateComputerCommand command) {
        loadVerifyDepartamentExistsByIdPort.existsById(command.departamentId());
        loadVerifyFacilityExistsByIdPort.existsById(command.facilityId());

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

        loadCreateComputerHistoryPort.CreateComputerHistory(
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
